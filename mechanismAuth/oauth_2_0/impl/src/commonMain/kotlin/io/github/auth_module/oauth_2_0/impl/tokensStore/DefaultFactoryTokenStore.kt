package io.github.auth_module.oauth_2_0.impl.tokensStore

import io.github.auth_module.oauth_2_0.core.tokensStore.FactoryTokenStore
import io.github.auth_module.oauth_2_0.core.tokensStore.TokensData
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