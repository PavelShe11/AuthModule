package io.github.auth_module.core.oauth2Client

data class OAuth2ClientConfig(
    val loginPath: String,
    val refreshTokenPath: String
)