package io.github.auth_module.oauth_2_0.core.oauth2Client.data

data class TokensResponse(
    val accessToken: String,
    val accessTokenExpires: Long,
    val refreshToken: String,
    val refreshTokenExpires: Long
)
