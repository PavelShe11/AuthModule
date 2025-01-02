package io.github.auth_module.jwt.tokensStore

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.io.files.Path

class DefaultFactoryTokenStore(
    private val path: Path
): FactoryTokenStore {
    override fun create(): KStore<TokensData> {
        return storeOf(path)
    }
}