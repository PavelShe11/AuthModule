package io.github.auth_module.impl

import io.github.auth_module.AuthManager
import io.github.auth_module.NetworkRequestCanceller
import io.github.auth_module.core.IOAuth2Engin
import io.github.auth_module.core.ITokensManager
import io.github.auth_module.core.oauth2Client.IOAuth2Client
import io.github.auth_module.core.oauth2Client.IRefreshTokenClient
import io.github.auth_module.core.oauth2Client.OAuth2ClientConfig
import io.github.auth_module.impl.tokensStore.TokenStoreConfig
import io.github.auth_module.impl.tokensStore.TokensStore

class OAuth2Engine<LoginData>: IOAuth2Engin<OAuth2EngineConfig, LoginData> {

    override lateinit var config: OAuth2EngineConfig

    override fun getAuthManager(networkRequestCanceller: NetworkRequestCanceller, authClient: IOAuth2Client<LoginData>): AuthManager<LoginData> {
        val tokensStore = getTokensStore()
        authClient.authConfig = config.toAuthConfig()
        return OAuth2Manager(authClient, tokensStore, networkRequestCanceller)
    }

    override fun getTokensManager(authClient: IRefreshTokenClient): ITokensManager {
        authClient.authConfig = config.toAuthConfig()
        val config = config.toTokensManagerConfig()
        val tokensStore = getTokensStore()
        return TokensManager(tokensStore, authClient, config)
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