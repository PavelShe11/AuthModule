package io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val email: String,
    val codeConfirm: String
)
