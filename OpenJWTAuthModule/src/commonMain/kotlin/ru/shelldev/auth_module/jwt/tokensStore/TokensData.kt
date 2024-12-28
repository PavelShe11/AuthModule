package ru.shelldev.auth_module.jwt.tokensStore

import kotlinx.serialization.Serializable

@Serializable
data class TokensData(
    val accessToken: String,
    val refreshToken: String,
    val tokenExpired: Long
)
