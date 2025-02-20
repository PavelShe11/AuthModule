package io.github.pavelshe11.auth_module.sampleApp.common.utils

fun String.ifNotBlank(callback: (String) -> Unit) {
    if (isNotBlank()) callback(this)
}