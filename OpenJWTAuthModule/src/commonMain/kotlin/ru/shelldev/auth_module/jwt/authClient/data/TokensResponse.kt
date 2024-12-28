package ru.shelldev.auth_module.jwt.authClient.data

import kotlinx.serialization.Serializable

@Serializable
data class TokensResponse(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val errorMessage: String? = null
)