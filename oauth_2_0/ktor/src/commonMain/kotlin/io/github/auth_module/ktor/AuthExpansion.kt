package io.github.auth_module.ktor

import io.github.auth_module.AuthManager
import io.github.auth_module.core.Auth
import io.github.auth_module.core.ITokensManager
import io.ktor.client.*

private lateinit var authClientProvider: AuthClientProvider

fun Auth.setAuthClientProvider(provider: AuthClientProvider) {
    authClientProvider = provider
}

fun Auth.getAuthManager(client: HttpClient): AuthManager {
    val networkRequestCanceller = KtorNetworkRequestCanceller(client)
    val authClient = authClientProvider.getAuthClient(client)
    return getAuthManager(networkRequestCanceller, authClient)
}

fun Auth.getTokensManager(client: HttpClient): ITokensManager {
    val authClient = authClientProvider.getAuthClient(client)
    return getTokensManager(authClient)
}