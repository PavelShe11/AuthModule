package ru.pavelshe11.auth_module.exception

import kotlin.jvm.JvmOverloads

class SomethingWentWrongException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
): Exception(message, cause)