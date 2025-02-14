package io.github.pavelshe11.auth_module.uikit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration

val MaterialTheme.textLinkStyles @Composable
get() = TextLinkStyles(
    typography.bodyMedium.toSpanStyle().copy(color = colorScheme.primary, textDecoration = TextDecoration.Underline),
    typography.bodyMedium.toSpanStyle().copy(color = colorScheme.inversePrimary, textDecoration = TextDecoration.Underline),
    typography.bodyMedium.toSpanStyle().copy(color = colorScheme.primaryContainer, textDecoration = TextDecoration.Underline),
    typography.bodyMedium.toSpanStyle().copy(color = colorScheme.onPrimaryContainer),
)