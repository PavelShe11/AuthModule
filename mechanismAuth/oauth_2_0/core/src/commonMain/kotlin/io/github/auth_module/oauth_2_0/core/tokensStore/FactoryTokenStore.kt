package io.github.auth_module.oauth_2_0.core.tokensStore

import io.github.xxfast.kstore.KStore

interface FactoryTokenStore {
    fun create(): KStore<TokensData>
}