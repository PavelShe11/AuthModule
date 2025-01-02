package io.github.auth_module.jwt.tokensStore

import kotlinx.serialization.Serializable

@Serializable
class TokensData(
    val accessToken: String,
    val refreshToken: String,
    val tokenExpired: Long
)
