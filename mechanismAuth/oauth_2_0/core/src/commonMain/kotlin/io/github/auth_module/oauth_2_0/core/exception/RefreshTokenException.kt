package io.github.auth_module.oauth_2_0.core.exception

import kotlin.jvm.JvmOverloads

sealed class RefreshTokenException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    class IsMissing @JvmOverloads constructor(
        message: String? = "Refresh token is missing",
        cause: Throwable? = null
    ) : RefreshTokenException(message, cause)

    class Expired @JvmOverloads constructor(
        message: String? = "Access token is expired",
        cause: Throwable? = null
    ) : RefreshTokenException(message, cause)

    class NetworkError @JvmOverloads constructor(
        message: String? = "Network error",
        cause: Throwable? = null
    ) : RefreshTokenException(message, cause)
}