package io.github.auth_module.oauth_2_0.core.oauth2Client

import io.github.auth_module.core.IAuthClient
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.TokensResponse

interface IOAuth2Client<LoginData> : IAuthClient<LoginData, TokensResponse, Any?>, IRefreshTokenClient {
    override var authConfig: OAuth2ClientConfig

    override suspend fun logout(): Any? {
        return null
    }
}

