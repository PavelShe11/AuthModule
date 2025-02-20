package io.github.pavelshe11.auth_module.sampleApp.common.exception

import kotlin.jvm.JvmOverloads

class NetworkException @JvmOverloads constructor(
    message: String?,
    cause: Throwable? = null
): Exception(message, cause)