package ru.pavelshe11.auth_module.jwt
import com.appstractive.jwt.JWT
import com.appstractive.jwt.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import ru.pavelshe11.auth_module.exception.SomethingWentWrongException
import ru.pavelshe11.auth_module.jwt.authClient.KtorAuthClient
import ru.pavelshe11.auth_module.jwt.authClient.data.RefreshTokenData
import ru.pavelshe11.auth_module.jwt.exception.AccessTokenException
import ru.pavelshe11.auth_module.jwt.exception.RefreshTokenException
import ru.pavelshe11.auth_module.jwt.tokensStore.TokensData
import ru.pavelshe11.auth_module.jwt.tokensStore.TokensStore
import kotlin.coroutines.cancellation.CancellationException

internal class TokensManager(
    private val store: TokensStore,
    private val client: KtorAuthClient
) {

    private val refreshTokenMutex = Mutex()

    suspend fun getActualAccessTokenOrNull(): String? = withContext(Dispatchers.Default) {
        var tokensData = store.get()
        val expired = tokensData?.tokenExpired ?: return@withContext null
        val currentTime = Clock.System.now().epochSeconds
        if (currentTime > expired) {
            try {
                refreshToken()
            } catch (ex: Exception) {
                return@withContext null
            }
            tokensData = store.get()
        }
        return@withContext tokensData?.accessToken
    }

    @Throws(SomethingWentWrongException::class, RefreshTokenException::class, AccessTokenException::class, CancellationException::class)
    suspend fun refreshToken() = withContext(Dispatchers.IO) {
        refreshTokenMutex.withLock {
            val tokensData = store.get()
            if (tokensData?.refreshToken == null || tokensData.refreshToken == "") {
                throw RefreshTokenException("There is no refresh Token in the token storage")
            }

            val newTokens = try {
                client.refreshToken(RefreshTokenData(tokensData.refreshToken))
            } catch (ex: CancellationException) {
                throw ex
            } catch (ex: Exception) {
                throw SomethingWentWrongException(cause = ex.cause)
            }

            if (newTokens.accessToken == null || newTokens.accessToken == "") {
                store.reset()
                throw AccessTokenException("The server returned an empty access token")
            }
            if (newTokens.refreshToken == null || newTokens.refreshToken == "") {
                store.reset()
                throw RefreshTokenException("The server returned an empty refresh token")
            }

            val jwtAccessToken = JWT.from(newTokens.accessToken)
            val expired = jwtAccessToken.claims["exp"]?.jsonPrimitive?.contentOrNull?.toLongOrNull()

            if (expired == null) {
                store.reset()
                throw AccessTokenException("There is no expiration time in the accessToken.")
            }

            store.save(TokensData(newTokens.accessToken, newTokens.refreshToken, expired))
        }
    }

}