package ru.pavelshe11.auth_module.jwt.authClient

data class AuthClientConfig(
    val loginPath: String,
    val refreshTokenPath: String
)