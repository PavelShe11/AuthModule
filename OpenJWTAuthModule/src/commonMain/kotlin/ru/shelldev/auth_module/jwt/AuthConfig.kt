package ru.shelldev.auth_module.jwt

import ru.shelldev.auth_module.jwt.tokensStore.FactoryTokenStore

data class AuthConfig internal constructor (
    val loginPath: String,
    val refreshTokenPath: String,
    val factoryTokenStore: FactoryTokenStore
)