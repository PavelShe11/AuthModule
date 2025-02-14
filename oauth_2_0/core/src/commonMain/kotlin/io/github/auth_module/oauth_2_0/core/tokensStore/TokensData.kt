package io.github.auth_module.oauth_2_0.core.tokensStore

import kotlinx.serialization.Serializable

@Serializable
class TokensData(
    val accessToken: String,
    val accessTokenExpired: Long,
    val refreshToken: String,
    val refreshTokenExpired: Long,
)
