package io.github.auth_module.oauth_2_0.core.oauth2Client

import io.github.auth_module.core.exception.SomethingWentWrongException
import io.github.auth_module.oauth_2_0.core.exception.RefreshTokenException
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.RefreshTokenData
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.TokensResponse
import kotlin.coroutines.cancellation.CancellationException

interface IRefreshTokenClient {
    var authConfig: OAuth2ClientConfig

    @Throws(SomethingWentWrongException::class, RefreshTokenException::class, CancellationException::class)
    suspend fun refreshToken(data: RefreshTokenData): TokensResponse
}