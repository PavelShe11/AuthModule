package io.github.auth_module

class Auth<EngineConfig, LoginData, LoginResponse, LogoutResponse, AuthClient: IAuthClient<LoginData, LoginResponse, LogoutResponse>>(
    val engine: IAuthEngine<EngineConfig, LoginData, LoginResponse, LogoutResponse, AuthClient>
) {

    fun init(
        authConfigBuilder: AuthConfigBuilder<EngineConfig>.() -> Unit
    ) {
        val builder = AuthConfigBuilder<EngineConfig>()
        builder.apply(authConfigBuilder)
        builder.engineConfig.invoke(engine.config)
    }

    fun getAuthManager(
        networkRequestCanceller: NetworkRequestCanceller,
        authClient: AuthClient
    ): AuthManager<LoginData> {
        return engine.getAuthManager(networkRequestCanceller, authClient)
    }

}