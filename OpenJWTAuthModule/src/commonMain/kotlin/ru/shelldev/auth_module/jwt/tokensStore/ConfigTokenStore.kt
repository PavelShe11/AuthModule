package ru.shelldev.auth_module.jwt.tokensStore

import kotlinx.io.files.Path

data class ConfigTokenStore(
    val tokenStorePath: Path
)
