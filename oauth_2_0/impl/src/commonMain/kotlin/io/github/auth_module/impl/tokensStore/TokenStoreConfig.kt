package io.github.auth_module.impl.tokensStore

import io.github.auth_module.core.tokensStore.FactoryTokenStore

internal data class TokenStoreConfig(
    val factoryTokenStore: FactoryTokenStore
)
