package io.github.auth_module.ktor.plugin

import io.github.auth_module.core.IProviderTokenManager
import io.github.auth_module.ktor.IRefreshTokenClientProvider

class TokenRefresherPluginConfig {
    val providerTokenManager: IProviderTokenManager? = null
    val refreshTokenClientProvider: IRefreshTokenClientProvider? = null
}