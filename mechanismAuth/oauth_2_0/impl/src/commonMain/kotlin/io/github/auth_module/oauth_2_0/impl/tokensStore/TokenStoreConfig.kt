package io.github.auth_module.oauth_2_0.impl.tokensStore

import io.github.auth_module.oauth_2_0.core.tokensStore.FactoryTokenStore

internal data class TokenStoreConfig(
    val factoryTokenStore: FactoryTokenStore
)
