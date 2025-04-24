package io.github.pavelshe11.auth_module.featureAuth.core.data

interface ConfigAuthClient {
    val sendCodeConfirmPath: String
    val loginPath: String
    val refreshTokenPath: String
}

