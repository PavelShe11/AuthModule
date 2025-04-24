package io.github.auth_module.oauth_2_0.core.oauth2Client

import io.github.auth_module.core.AuthClient
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.TokensResponse

interface OAuth2Client<LoginData> : AuthClient<LoginData, TokensResponse, Unit>, RefreshTokenClient {
    override suspend fun logout() = Unit
}

