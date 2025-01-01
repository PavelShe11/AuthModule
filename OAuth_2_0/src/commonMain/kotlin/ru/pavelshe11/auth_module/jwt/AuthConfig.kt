package ru.pavelshe11.auth_module.jwt

import ru.pavelshe11.auth_module.jwt.tokensStore.FactoryTokenStore

class AuthConfig internal constructor (
    val loginPath: String,
    val refreshTokenPath: String,
    val factoryTokenStore: FactoryTokenStore
)