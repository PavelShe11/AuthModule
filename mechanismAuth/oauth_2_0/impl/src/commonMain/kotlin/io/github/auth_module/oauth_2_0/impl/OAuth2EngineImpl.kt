package io.github.auth_module.oauth_2_0.impl

import io.github.auth_module.core.AuthManager
import io.github.auth_module.core.NetworkRequestCanceller
import io.github.auth_module.oauth_2_0.core.OAuth2Config
import io.github.auth_module.oauth_2_0.core.OAuth2Engine
import io.github.auth_module.oauth_2_0.core.TokensManager.TokensManager
import io.github.auth_module.oauth_2_0.core.oauth2Client.OAuth2Client
import io.github.auth_module.oauth_2_0.core.oauth2Client.RefreshTokenClient
import io.github.auth_module.oauth_2_0.impl.tokensStore.TokenStoreConfig
import io.github.auth_module.oauth_2_0.impl.tokensStore.TokensStore

class DefaultOAuth2Engine<LoginData>: OAuth2Engine<OAuth2Config, LoginData> {

    override lateinit var config: OAuth2Config

    override fun getAuthManager(networkRequestCanceller: NetworkRequestCanceller, authClient: OAuth2Client<LoginData>): AuthManager<LoginData> {
        val tokensStore = getTokensStore()
        return OAuth2Manager(authClient, tokensStore, networkRequestCanceller)
    }

    override fun getTokensManager(authClient: RefreshTokenClient): TokensManager {
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

    private fun OAuth2Config.toTokensManagerConfig(): TokensManagerConfig {
        return TokensManagerConfig(
            setOf(loginPath, refreshTokenPath),
        )
    }

}