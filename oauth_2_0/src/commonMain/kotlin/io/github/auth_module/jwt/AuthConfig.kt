package io.github.auth_module.jwt

import io.github.auth_module.jwt.tokensStore.FactoryTokenStore

class AuthConfig internal constructor (
    val loginPath: String,
    val refreshTokenPath: String,
    val factoryTokenStore: FactoryTokenStore
)