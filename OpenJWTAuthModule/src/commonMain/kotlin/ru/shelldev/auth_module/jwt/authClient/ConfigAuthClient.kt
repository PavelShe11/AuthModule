package ru.shelldev.auth_module.jwt.authClient

data class ConfigAuthClient(
    val loginPath: String,
    val refreshTokenPath: String
)