package ru.shelldev.auth_module.jwt

import ru.shelldev.auth_module.jwt.tokensStore.FactoryTokenStore

class AuthBuilder {
    private var loginPath = "/login"
    private var refreshTokenPath = "/refreshToken"
    private var factoryTokenStore: FactoryTokenStore? = null

    fun setLoginPath(loginPath: String) = apply { this.loginPath = loginPath }
    fun setRefreshTokenPath(refreshTokenPath: String) = apply { this.refreshTokenPath = refreshTokenPath }
    fun setFactoryTokenStore(factoryTokenStore: FactoryTokenStore) = apply { this.factoryTokenStore = factoryTokenStore }

    fun build(): AuthConfig {
        if (factoryTokenStore == null)
            throw IllegalArgumentException("FactoryTokenStore is not installed!")

        return AuthConfig(loginPath, refreshTokenPath, factoryTokenStore!!)
    }

}