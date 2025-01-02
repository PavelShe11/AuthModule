package io.github.auth_module.exception

import kotlin.jvm.JvmOverloads

class LogoutException @JvmOverloads constructor(
    message: String?,
    cause: Throwable? = null
): Exception(message, cause)
