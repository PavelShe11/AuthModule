package io.github.auth_module.core

import io.github.auth_module.core.exception.AccessTokenException
import io.github.auth_module.core.exception.RefreshTokenException
import io.github.auth_module.exception.SomethingWentWrongException
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
}