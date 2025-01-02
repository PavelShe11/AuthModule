package io.github.auth_module.jwt

import io.github.auth_module.jwt.tokensStore.FactoryTokenStore

class AuthBuilder {
    var loginPath = "/login"
    var refreshTokenPath = "/refreshToken"
    var factoryTokenStore: FactoryTokenStore? = null

    fun build(): AuthConfig {
        if (factoryTokenStore == null)
            throw IllegalArgumentException("FactoryTokenStore is not installed!")

        return AuthConfig(loginPath, refreshTokenPath, factoryTokenStore!!)
    }

}