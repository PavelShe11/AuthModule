package ru.pavelshe11.auth_module.jwt.authClient.data

import kotlinx.serialization.Serializable

@Serializable
internal data class TokensResponse(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val errorMessage: String? = null
)
