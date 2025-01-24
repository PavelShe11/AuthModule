package io.github.auth_module

interface IAuthEngine <EngineConfig, LoginData, LoginResponse, LogoutResponse, AuthClient: IAuthClient<LoginData, LoginResponse, LogoutResponse>> {
    var config: EngineConfig

    fun getAuthManager(
        networkRequestCanceller: NetworkRequestCanceller,
        authClient: AuthClient
    ): AuthManager<LoginData>
}