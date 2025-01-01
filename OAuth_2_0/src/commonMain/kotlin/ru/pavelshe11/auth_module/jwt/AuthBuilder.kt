package ru.pavelshe11.auth_module.jwt

import ru.pavelshe11.auth_module.jwt.tokensStore.FactoryTokenStore

internal class AuthBuilder {
    var loginPath = "/login"
    var refreshTokenPath = "/refreshToken"
    var factoryTokenStore: FactoryTokenStore? = null

    fun build(): AuthConfig {
        if (factoryTokenStore == null)
            throw IllegalArgumentException("FactoryTokenStore is not installed!")

        return AuthConfig(loginPath, refreshTokenPath, factoryTokenStore!!)
    }

}