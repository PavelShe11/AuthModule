package io.github.auth_module.oauth_2_0.core

import io.github.auth_module.core.exception.SomethingWentWrongException
import io.github.auth_module.oauth_2_0.core.exception.AccessTokenException
import io.github.auth_module.oauth_2_0.core.exception.RefreshTokenException
import kotlin.coroutines.cancellation.CancellationException

interface ITokensManager {
    suspend fun getActualAccessTokenOrNull(): String?

    @Throws(
        SomethingWentWrongException::class,
        RefreshTokenException::class,
        AccessTokenException::class,
        CancellationException::class
    )
    suspend fun refreshToken()

    fun ignoredPathsForTokenRefresh(): Set<String>
}