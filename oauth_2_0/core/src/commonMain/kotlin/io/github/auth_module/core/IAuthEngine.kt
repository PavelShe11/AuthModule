package io.github.auth_module.core

import io.github.auth_module.AuthManager
import io.github.auth_module.core.authClient.IAuthClient

interface IAuthEngine {
    var config: AuthConfig
    fun getAuthManager(networkRequestCanceller: NetworkRequestCanceller, authClient: IAuthClient): AuthManager
    fun getTokensManager(authClient: IAuthClient): ITokensManager
}