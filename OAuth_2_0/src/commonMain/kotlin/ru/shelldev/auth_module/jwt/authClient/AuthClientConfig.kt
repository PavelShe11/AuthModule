package ru.shelldev.auth_module.jwt.authClient

data class AuthClientConfig(
    val loginPath: String,
    val refreshTokenPath: String
)