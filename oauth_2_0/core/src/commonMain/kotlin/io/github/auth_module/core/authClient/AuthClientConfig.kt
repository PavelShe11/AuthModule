package io.github.auth_module.core.authClient

data class AuthClientConfig(
    val loginPath: String,
    val refreshTokenPath: String
)