package io.github.auth_module.core.authClient

import io.github.auth_module.core.authClient.data.RefreshTokenData
import io.github.auth_module.core.authClient.data.TokensResponse
import io.github.auth_module.core.exception.RefreshTokenException
import io.github.auth_module.exception.LoginException
import io.github.auth_module.exception.SomethingWentWrongException
import kotlin.coroutines.cancellation.CancellationException

interface IAuthClient {
    var authConfig: AuthClientConfig

    @Throws(SomethingWentWrongException::class, LoginException::class, CancellationException::class)
    suspend fun <LoginData>login(data: LoginData): TokensResponse

    @Throws(SomethingWentWrongException::class, RefreshTokenException::class, CancellationException::class)
    suspend fun refreshToken(data: RefreshTokenData): TokensResponse
}

