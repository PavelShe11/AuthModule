package io.github.auth_module.core.exception

import kotlin.jvm.JvmOverloads

class SomethingWentWrongException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
): Exception(message, cause)