package ru.pavelshe11.auth_module.jwt

import io.ktor.client.HttpClient
import ru.pavelshe11.auth_module.AuthManager
import ru.pavelshe11.auth_module.jwt.authClient.AuthClientConfig
import ru.pavelshe11.auth_module.jwt.authClient.KtorAuthClient
import ru.pavelshe11.auth_module.jwt.tokensStore.TokenStoreConfig
import ru.pavelshe11.auth_module.jwt.tokensStore.TokensStore

object Auth {

    private lateinit var _config: AuthConfig

    val config: AuthConfig get() {
        if (!::_config.isInitialized) {
            throw IllegalStateException("Library is not initialized. Call init() first.")
        }
        return _config
    }

    fun init(config: AuthConfig) {
        this._config = config
    }

    fun init(authBuilder: AuthBuilder.() -> Unit) {
        val builder = AuthBuilder()
        authBuilder(builder)
        init(builder.build())
    }

    fun getAuthManager(httpClient: HttpClient): AuthManager {
        val authClient = getKtorAuthClient(httpClient)
        val tokensStore = getTokensStore()
        return OpenJWTAuthManager(authClient, tokensStore, httpClient)
    }

    internal fun getTokensManager(httpClient: HttpClient): TokensManager {
        val tokensStore = getTokensStore()
        val authClient = getKtorAuthClient(httpClient)
        return TokensManager(tokensStore, authClient)
    }

    private fun getKtorAuthClient(client: HttpClient): KtorAuthClient {
        val authClientConfig = AuthClientConfig(
            config.loginPath,
            config.refreshTokenPath
        )
        return KtorAuthClient(client, authClientConfig)
    }

    private fun getTokensStore(): TokensStore {
        val tokensStoreConfig = TokenStoreConfig(
            config.factoryTokenStore
        )
        return TokensStore(tokensStoreConfig)
    }

}