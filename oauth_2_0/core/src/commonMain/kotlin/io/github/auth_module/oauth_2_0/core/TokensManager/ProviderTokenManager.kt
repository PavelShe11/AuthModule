package io.github.auth_module.oauth_2_0.core.TokensManager

import io.github.auth_module.oauth_2_0.core.oauth2Client.RefreshTokenClient

interface ProviderTokenManager {
    fun getTokensManager(authClient: RefreshTokenClient): TokensManager
}