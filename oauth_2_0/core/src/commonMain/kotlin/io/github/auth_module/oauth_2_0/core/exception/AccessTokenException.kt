package io.github.auth_module.oauth_2_0.core.exception

import kotlin.jvm.JvmOverloads

sealed class AccessTokenException @JvmOverloads constructor(
    message: String?,
    cause: Throwable? = null
) : Exception(message, cause) {
    class IsMissing @JvmOverloads constructor(
        message: String? = "Access token is missing",
        cause: Throwable? = null
    ) : AccessTokenException(message, cause)

    class Expired @JvmOverloads constructor(
        message: String? = "Access token is expired",
        cause: Throwable? = null
    ) : AccessTokenException(message, cause)
}
