package io.github.auth_module.oauth_2_0.ktor.plugin

import io.github.auth_module.oauth_2_0.core.IProviderTokenManager
import io.github.auth_module.oauth_2_0.ktor.IRefreshTokenClientProvider

class TokenRefresherPluginConfig {
    val providerTokenManager: IProviderTokenManager? = null
    val refreshTokenClientProvider: IRefreshTokenClientProvider? = null
}