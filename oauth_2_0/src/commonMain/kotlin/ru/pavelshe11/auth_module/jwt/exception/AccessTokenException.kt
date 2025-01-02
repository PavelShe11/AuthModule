package ru.pavelshe11.auth_module.jwt.exception

import kotlin.jvm.JvmOverloads

internal class AccessTokenException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)
