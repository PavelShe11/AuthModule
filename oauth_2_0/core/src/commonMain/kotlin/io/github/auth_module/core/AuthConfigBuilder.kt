package io.github.auth_module.core

import io.github.auth_module.core.tokensStore.FactoryTokenStore

class AuthConfigBuilder {
    var loginPath = "/login"
    var refreshTokenPath = "/refreshToken"
    var factoryTokenStore: FactoryTokenStore? = null

    fun build(): AuthConfig {
        if (factoryTokenStore == null)
            throw IllegalArgumentException("FactoryTokenStore is not installed!")

        return AuthConfig(loginPath, refreshTokenPath, factoryTokenStore!!)
    }

}