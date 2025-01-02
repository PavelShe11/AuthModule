package io.github.auth_module.jwt.exception

import kotlin.jvm.JvmOverloads

internal class RefreshTokenException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
): Exception(message, cause)