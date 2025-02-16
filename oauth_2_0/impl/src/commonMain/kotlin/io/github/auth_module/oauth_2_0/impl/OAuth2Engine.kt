package io.github.auth_module.oauth_2_0.impl

import io.github.auth_module.core.AuthManager
import io.github.auth_module.core.NetworkRequestCanceller
import io.github.auth_module.oauth_2_0.core.OAuth2Engin
import io.github.auth_module.oauth_2_0.core.TokensManager.TokensManager
import io.github.auth_module.oauth_2_0.core.oauth2Client.OAuth2Client
import io.github.auth_module.oauth_2_0.core.oauth2Client.OAuth2ClientConfig
import io.github.auth_module.oauth_2_0.core.oauth2Client.RefreshTokenClient
import io.github.auth_module.oauth_2_0.impl.tokensStore.TokenStoreConfig
import io.github.auth_module.oauth_2_0.impl.tokensStore.TokensStore

class OAuth2Engine<LoginData>: OAuth2Engin<OAuth2EngineConfig, LoginData> {

    override lateinit var config: OAuth2EngineConfig

    override fun getAuthManager(networkRequestCanceller: NetworkRequestCanceller, authClient: OAuth2Client<LoginData>): AuthManager<LoginData> {
        val tokensStore = getTokensStore()
        authClient.authConfig = config.toAuthConfig()
        return OAuth2Manager(authClient, tokensStore, networkRequestCanceller)
    }

    override fun getTokensManager(authClient: RefreshTokenClient): TokensManager {
        authClient.authConfig = config.toAuthConfig()
        val config = config.toTokensManagerConfig()
        val tokensStore = getTokensStore()
        return TokensManagerImpl(tokensStore, authClient, config)
    }

    private fun getTokensStore(): TokensStore {
        val tokensStoreConfig = TokenStoreConfig(
            config.factoryTokenStore
        )
        return TokensStore(tokensStoreConfig)
    }

    private fun OAuth2EngineConfig.toAuthConfig(): OAuth2ClientConfig {
        return OAuth2ClientConfig(loginPath, refreshTokenPath)
    }

    private fun OAuth2EngineConfig.toTokensManagerConfig(): TokensManagerConfig {
        return TokensManagerConfig(
            setOf(loginPath, refreshTokenPath),
        )
    }

}