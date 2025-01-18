package io.github.auth_module.impl

import io.github.auth_module.AuthManager
import io.github.auth_module.core.AuthConfig
import io.github.auth_module.core.IAuthEngine
import io.github.auth_module.core.ITokensManager
import io.github.auth_module.core.NetworkRequestCanceller
import io.github.auth_module.core.authClient.AuthClientConfig
import io.github.auth_module.core.authClient.IAuthClient
import io.github.auth_module.impl.tokensStore.TokenStoreConfig
import io.github.auth_module.impl.tokensStore.TokensStore

object AuthEngine: IAuthEngine {

    override lateinit var config: AuthConfig

    override fun getAuthManager(networkRequestCanceller: NetworkRequestCanceller, authClient: IAuthClient): AuthManager {
        val tokensStore = getTokensStore()
        return OAuth2_0Manager(authClient, tokensStore, networkRequestCanceller)
    }

    override fun getTokensManager(authClient: IAuthClient): ITokensManager {
        authClient.authConfig = config.toAuthConfig()
        val tokensStore = getTokensStore()
        return TokensManager(tokensStore, authClient)
    }

    private fun getTokensStore(): TokensStore {
        val tokensStoreConfig = TokenStoreConfig(
            config.factoryTokenStore
        )
        return TokensStore(tokensStoreConfig)
    }

    private fun AuthConfig.toAuthConfig(): AuthClientConfig {
        return AuthClientConfig(loginPath, refreshTokenPath)
    }



}