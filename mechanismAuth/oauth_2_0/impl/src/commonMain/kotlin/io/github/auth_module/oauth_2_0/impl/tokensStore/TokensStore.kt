package io.github.auth_module.oauth_2_0.impl.tokensStore

import io.github.auth_module.oauth_2_0.core.tokensStore.TokensData
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class TokensStore(
    config: TokenStoreConfig
) {

    private val store: KStore<TokensData> = config.factoryTokenStore.create()

    suspend fun save(data: TokensData) = withContext(Dispatchers.IO){
        store.set(data)
    }

    suspend fun get(): TokensData? = withContext(Dispatchers.IO){
        return@withContext store.get()
    }

    suspend fun reset() = withContext(Dispatchers.IO) {
        store.reset()
    }

}