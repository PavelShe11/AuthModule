package io.github.auth_module.core.exception

import kotlin.jvm.JvmOverloads

class LoginException @JvmOverloads constructor(
    message: String?,
    cause: Throwable? = null
): Exception(message, cause)