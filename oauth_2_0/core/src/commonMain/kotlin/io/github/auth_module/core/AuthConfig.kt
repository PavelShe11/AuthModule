package io.github.auth_module.core

import io.github.auth_module.core.tokensStore.FactoryTokenStore

class AuthConfig constructor (
    val loginPath: String,
    val refreshTokenPath: String,
    val factoryTokenStore: FactoryTokenStore,
)