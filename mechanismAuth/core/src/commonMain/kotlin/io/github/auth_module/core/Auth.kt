package io.github.auth_module.core

class Auth<EngineConfig, LoginData, LoginResponse, LogoutResponse, AuthClient: io.github.auth_module.core.AuthClient<LoginData, LoginResponse, LogoutResponse>>(
    val engine: AuthEngine<EngineConfig, LoginData, LoginResponse, LogoutResponse, AuthClient>
) {

    fun init(
        authConfigBuilder: AuthConfigBuilder<EngineConfig>.() -> Unit
    ): Auth<EngineConfig, LoginData, LoginResponse, LogoutResponse, AuthClient> {
        val builder = AuthConfigBuilder<EngineConfig>()
        builder.apply(authConfigBuilder)
        builder.engineConfig.invoke(engine.config)
        return this
    }

    fun getAuthManager(
        networkRequestCanceller: NetworkRequestCanceller,
        authClient: AuthClient
    ): AuthManager<LoginData> {
        return engine.getAuthManager(networkRequestCanceller, authClient)
    }

}