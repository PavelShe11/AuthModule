package io.github.auth_module.core

interface AuthEngine <EngineConfig, LoginData, LoginResponse, LogoutResponse, AuthClient: io.github.auth_module.core.AuthClient<LoginData, LoginResponse, LogoutResponse>> {
    var config: EngineConfig

    fun getAuthManager(
        networkRequestCanceller: NetworkRequestCanceller,
        authClient: AuthClient
    ): AuthManager<LoginData>
}