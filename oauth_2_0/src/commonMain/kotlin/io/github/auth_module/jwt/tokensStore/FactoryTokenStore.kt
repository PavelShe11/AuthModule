package io.github.auth_module.jwt.tokensStore

import io.github.xxfast.kstore.KStore

interface FactoryTokenStore {
    fun create(): KStore<TokensData>
}