package io.github.pavelshe11.auth_module.featureAuth.ui.screens.registrationScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.pavelshe11.auth_module.featureAuth.presentation.registrationScreen.OnlyUIRegistrationComponentFactory
import io.github.pavelshe11.auth_module.featureAuth.ui.screen.RegistrationUI
import io.github.pavelshe11.auth_module.uikit.theme.StudBridgeTheme

@Preview
@Composable
private fun PreviewRegistrationUI() {
    StudBridgeTheme {
        RegistrationUI(
            OnlyUIRegistrationComponentFactory().invoke(
                {}
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}