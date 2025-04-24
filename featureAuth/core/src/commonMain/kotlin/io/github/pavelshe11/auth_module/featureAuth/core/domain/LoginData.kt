package io.github.pavelshe11.auth_module.featureAuth.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val email: String,
    val codeConfirm: String
)
