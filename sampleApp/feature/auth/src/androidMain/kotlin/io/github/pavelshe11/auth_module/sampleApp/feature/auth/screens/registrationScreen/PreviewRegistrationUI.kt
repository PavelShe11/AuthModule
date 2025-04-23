package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screens.registrationScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.registrationScreen.OnlyUIRegistrationComponent
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.registrationScreen.RegistrationUI
import io.github.pavelshe11.auth_module.sampleApp.uikit.theme.StudBridgeTheme

@Preview
@Composable
private fun PreviewRegistrationUI() {
    StudBridgeTheme {
        RegistrationUI(
            OnlyUIRegistrationComponent(
                {}
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}