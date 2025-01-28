package io.github.pavelshe11.auth_module.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(colors = lightColors(primary = Color(0xFF1D61E7))) {
        Box(Modifier.padding(WindowInsets.statusBars.asPaddingValues(LocalDensity.current))) {
            var countryInput by remember { mutableStateOf(TextFieldValue("")) }
            var phoneInput by remember { mutableStateOf(TextFieldValue("")) }
            LoginScreen(
                countryInput,
                { countryInput = it },
                phoneInput,
                { phoneInput = it },
                {}
            )
        }
    }
}