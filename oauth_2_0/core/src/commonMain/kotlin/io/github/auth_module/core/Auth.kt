package io.github.auth_module.core

import io.github.auth_module.AuthManager
import io.github.auth_module.core.authClient.IAuthClient

object Auth {

    private lateinit var _config: AuthConfig
    private lateinit var engine: IAuthEngine

    val config: AuthConfig
        get() {
        if (!Auth::_config.isInitialized) {
            throw IllegalStateException("Library is not initialized. Call init() first.")
        }
        return _config
    }

    fun init(config: AuthConfig) {
        _config = config
    }

    fun init(authBuilder: AuthConfigBuilder.() -> Unit) {
        val builder = AuthConfigBuilder()
        authBuilder(builder)
        init(builder.build())
    }

    fun setEngine(engine: IAuthEngine) {
        Auth.engine = engine
    }

    fun getAuthManager(networkRequestCanceller: NetworkRequestCanceller, authClient: IAuthClient): AuthManager {
        return engine.getAuthManager(networkRequestCanceller, authClient)
    }

    fun getTokensManager(authClient: IAuthClient): ITokensManager {
        return engine.getTokensManager(authClient)
    }

}