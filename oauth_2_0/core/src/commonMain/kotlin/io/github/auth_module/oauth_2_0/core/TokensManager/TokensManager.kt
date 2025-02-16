package io.github.auth_module.oauth_2_0.core.TokensManager

import io.github.auth_module.oauth_2_0.core.exception.AccessTokenException
import io.github.auth_module.oauth_2_0.core.exception.RefreshTokenException
import kotlin.coroutines.cancellation.CancellationException

interface TokensManager {

    @Throws(
        AccessTokenException::class,
        CancellationException::class
    )
    suspend fun getActualAccessToken(): String

    @Throws(
        RefreshTokenException::class,
        CancellationException::class
    )
    suspend fun refreshToken()

    fun ignoredPathsForTokenRefresh(): Set<String>

}