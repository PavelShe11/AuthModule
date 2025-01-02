package io.github.auth_module.jwt.authClient.data

import kotlinx.serialization.Serializable

@Serializable
internal data class RefreshTokenData (
    val refreshToken: String
)