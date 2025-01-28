package io.github.pavelshe11.auth_module.sample

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(device = "id:pixel_5", showBackground = true, showSystemUi = true)
fun LoginScreenPreview() {
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

