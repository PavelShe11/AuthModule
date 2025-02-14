package io.github.auth_module.oauth_2_0.core.oauth2Client

data class OAuth2ClientConfig(
    val loginPath: String,
    val refreshTokenPath: String
)