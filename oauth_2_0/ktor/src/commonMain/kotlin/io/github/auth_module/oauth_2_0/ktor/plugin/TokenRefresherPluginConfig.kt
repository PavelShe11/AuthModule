package io.github.auth_module.oauth_2_0.ktor.plugin

import io.github.auth_module.oauth_2_0.core.TokensManager.ProviderTokenManager
import io.github.auth_module.oauth_2_0.ktor.RefreshTokenClientProvider

class TokenRefresherPluginConfig {
    val providerTokenManager: ProviderTokenManager? = null
    val refreshTokenClientProvider: RefreshTokenClientProvider? = null
}