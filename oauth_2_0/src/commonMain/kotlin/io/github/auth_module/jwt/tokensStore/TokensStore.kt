package io.github.auth_module.jwt.tokensStore

import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class TokensStore(
    private val config: TokenStoreConfig
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