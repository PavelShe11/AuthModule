package ru.shelldev.auth_module.jwt.exception

import kotlin.jvm.JvmOverloads

class AccessTokenException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)
