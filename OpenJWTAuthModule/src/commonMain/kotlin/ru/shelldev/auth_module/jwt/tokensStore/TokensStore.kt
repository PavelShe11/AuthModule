package ru.shelldev.auth_module.jwt.tokensStore

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class TokensStore(
    private val config: ConfigTokenStore
) {

    private val store: KStore<TokensData> = storeOf(file = config.tokenStorePath)

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