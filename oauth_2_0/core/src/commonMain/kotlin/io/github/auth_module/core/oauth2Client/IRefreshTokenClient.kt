package io.github.auth_module.core.oauth2Client

import io.github.auth_module.core.exception.RefreshTokenException
import io.github.auth_module.core.oauth2Client.data.RefreshTokenData
import io.github.auth_module.core.oauth2Client.data.TokensResponse
import io.github.auth_module.exception.SomethingWentWrongException
import kotlin.coroutines.cancellation.CancellationException

interface IRefreshTokenClient {
    var authConfig: OAuth2ClientConfig

    @Throws(SomethingWentWrongException::class, RefreshTokenException::class, CancellationException::class)
    suspend fun refreshToken(data: RefreshTokenData): TokensResponse
}