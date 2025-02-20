package io.github.pavelshe11.auth_module.sampleApp.feature.auth.data

interface ConfigAuthClient {
    val sendCodeConfirmPath: String
    val loginPath: String
    val refreshTokenPath: String
}

