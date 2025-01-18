package io.github.auth_module.core.tokensStore

import kotlinx.serialization.Serializable

@Serializable
class TokensData(
    val accessToken: String,
    val accessTokenExpired: Long,
    val refreshToken: String,
    val refreshTokenExpired: Long,
)
