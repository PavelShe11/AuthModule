package io.github.auth_module.impl

import io.github.auth_module.core.ITokensManager
import io.github.auth_module.core.exception.AccessTokenException
import io.github.auth_module.core.exception.RefreshTokenException
import io.github.auth_module.core.oauth2Client.IRefreshTokenClient
import io.github.auth_module.core.oauth2Client.data.RefreshTokenData
import io.github.auth_module.core.tokensStore.TokensData
import io.github.auth_module.exception.SomethingWentWrongException
import io.github.auth_module.impl.tokensStore.TokensStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlin.coroutines.cancellation.CancellationException

internal class TokensManager(
    private val store: TokensStore,
    private val client: IRefreshTokenClient,
    private val config: TokensManagerConfig
) : ITokensManager {
    private val lock = Mutex()

    override suspend fun getActualAccessTokenOrNull(): String? = withContext(Dispatchers.Default) {
        return@withContext lock.withLock {
            var tokensData = store.get()
            val expired = tokensData?.accessTokenExpired ?: return@withContext null
            val currentTime = Clock.System.now().epochSeconds
            if (currentTime > expired) {
                try {
                    updateTokens()
                } catch (ex: Exception) {
                    return@withContext null
                } catch (ex: CancellationException) {
                    throw ex
                }
                tokensData = store.get()
            }
            tokensData?.accessToken
        }
    }

    @Throws(
        SomethingWentWrongException::class,
        RefreshTokenException::class,
        AccessTokenException::class,
        CancellationException::class
    )
    override suspend fun refreshToken() {
        lock.withLock {
            updateTokens()
        }
    }

    @Throws(
        SomethingWentWrongException::class,
        RefreshTokenException::class,
        AccessTokenException::class,
        CancellationException::class
    )
    private suspend fun updateTokens() = withContext(Dispatchers.IO) {
        val tokensData = store.get()
        if (tokensData?.refreshToken == null || tokensData.refreshToken == "") {
            throw RefreshTokenException("There is no refresh Token in the token storage")
        }

        val newTokens = client.refreshToken(RefreshTokenData(tokensData.refreshToken))

        if (newTokens.accessToken.isNullOrEmpty()) {
            store.reset()
            throw AccessTokenException("The server returned an empty access token")
        }

        if (newTokens.refreshToken.isNullOrEmpty()) {
            store.reset()
            throw RefreshTokenException("The server returned an empty refresh token")
        }

        if (newTokens.accessTokenExpires == null) {
            store.reset()
            throw AccessTokenException("There is no expiration time in the accessToken")
        }

        if (newTokens.refreshTokenExpires == null) {
            store.reset()
            throw RefreshTokenException("There is no expiration time in the refreshToken")
        }

        store.save(
            TokensData(
                newTokens.accessToken!!,
                newTokens.accessTokenExpires!!,
                newTokens.refreshToken!!,
                newTokens.refreshTokenExpires!!
            )
        )
    }

    override fun ignoredPathsForTokenRefresh(): Set<String> {
        return config.ignoredPathsForTokenRefresh
    }
}