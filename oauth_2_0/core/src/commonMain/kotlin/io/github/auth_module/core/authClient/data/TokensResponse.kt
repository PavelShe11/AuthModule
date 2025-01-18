package io.github.auth_module.core.authClient.data

data class TokensResponse(
    val accessToken: String? = null,
    val accessTokenExpires: Long? = null,
    val refreshToken: String? = null,
    val refreshTokenExpires: Long? = null
)
