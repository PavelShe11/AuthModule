package io.github.auth_module.oauth_2_0.impl

import io.github.auth_module.core.exception.NetworkException
import io.github.auth_module.oauth_2_0.core.TokensManager.TokensManager
import io.github.auth_module.oauth_2_0.core.exception.AccessTokenException
import io.github.auth_module.oauth_2_0.core.exception.RefreshTokenException
import io.github.auth_module.oauth_2_0.core.oauth2Client.RefreshTokenClient
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.RefreshTokenData
import io.github.auth_module.oauth_2_0.core.tokensStore.TokensData
import io.github.auth_module.oauth_2_0.impl.tokensStore.TokensStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlin.coroutines.cancellation.CancellationException

internal class TokensManagerImpl(
    private val store: TokensStore,
    private val client: RefreshTokenClient,
    private val config: TokensManagerConfig
) : TokensManager {

    private val lock = Mutex()

    override suspend fun getActualAccessToken(): String = withContext(Dispatchers.Default) {
        return@withContext lock.withLock {
            var tokensData = store.get() ?: throw AccessTokenException.IsMissing()

            val expired = tokensData.accessTokenExpired
            val currentTime = Clock.System.now().epochSeconds
            if (currentTime > expired) {
                try {
                    updateTokens()
                    tokensData = store.get() ?: throw AccessTokenException.IsMissing()
                } catch (e: RefreshTokenException) {
                    when(e) {
                        is RefreshTokenException.Expired,
                        is RefreshTokenException.IsMissing -> throw AccessTokenException.IsMissing(cause = e)
                        is RefreshTokenException.NetworkError -> throw AccessTokenException.Expired(cause = e)
                    }
                }
            }

            tokensData.accessToken
        }
    }

    override suspend fun refreshToken() {
        lock.withLock {
            updateTokens()
        }
    }

    @Throws(
        RefreshTokenException::class,
        CancellationException::class
    )
    private suspend fun updateTokens() = withContext(Dispatchers.IO) {
        val tokensData = store.get() ?: throw RefreshTokenException.IsMissing()

        val expired = tokensData.refreshTokenExpired
        val currentTime = Clock.System.now().epochSeconds
        if (currentTime > expired) {
            throw RefreshTokenException.Expired()
        }

        try {
            val newTokens = client.refreshToken(RefreshTokenData(tokensData.refreshToken))
            store.save(
                TokensData(
                    newTokens.accessToken,
                    newTokens.accessTokenExpires,
                    newTokens.refreshToken,
                    newTokens.refreshTokenExpires
                )
            )
        } catch (ex: NetworkException) {
            throw RefreshTokenException.NetworkError(cause = ex)
        }
    }

    override fun ignoredPathsForTokenRefresh(): Set<String> {
        return config.ignoredPathsForTokenRefresh
    }
}