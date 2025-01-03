package io.github.auth_module.jwt
import com.appstractive.jwt.JWT
import com.appstractive.jwt.from
import io.ktor.client.HttpClient
import kotlinx.coroutines.cancelChildren
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import io.github.auth_module.AuthManager
import io.github.auth_module.KSerializable
import io.github.auth_module.exception.LoginException
import io.github.auth_module.exception.SomethingWentWrongException
import io.github.auth_module.jwt.authClient.KtorAuthClient
import io.github.auth_module.jwt.tokensStore.TokensData
import io.github.auth_module.jwt.tokensStore.TokensStore
import kotlin.coroutines.cancellation.CancellationException

internal class OpenJWTAuthManager(
    private val authClient: KtorAuthClient,
    private val tokensStore: TokensStore,
    private val ktorClient: HttpClient
): AuthManager {

    override suspend fun <LoginData: KSerializable<LoginData>> login(data: LoginData) {
        val result = try {
            authClient.login(data)
        } catch (ex: CancellationException) {
            throw ex
        } catch (ex: Exception) {
            throw SomethingWentWrongException(cause = ex)
        }

        if (!result.errorMessage.isNullOrEmpty()) {
            throw LoginException(result.errorMessage)
        }
        if (result.accessToken.isNullOrEmpty() || result.refreshToken.isNullOrEmpty()) {
            throw SomethingWentWrongException("Tokens are missing")
        }

        val accessTokenJwt = JWT.from(result.accessToken)
        val expire = accessTokenJwt.claims["exp"]?.jsonPrimitive?.contentOrNull?.toLongOrNull()
            ?: throw SomethingWentWrongException("The access token is missing the expired field")

        val tokensData = TokensData(
            result.accessToken,
            result.refreshToken,
            expire
        )

        tokensStore.save(tokensData)
    }

    override suspend fun logout() {
        ktorClient.coroutineContext.cancelChildren()
        tokensStore.reset()
    }

}