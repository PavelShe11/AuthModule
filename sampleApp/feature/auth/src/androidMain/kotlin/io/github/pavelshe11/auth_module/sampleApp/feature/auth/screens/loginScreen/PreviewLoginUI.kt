package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screens.loginScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen.LoginUI
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen.OnlyUILoginComponent
import io.github.pavelshe11.auth_module.sampleApp.uikit.theme.StudBridgeTheme

@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginUI() {
    StudBridgeTheme {
        LoginUI(
            OnlyUILoginComponent(
                {},
                {}
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        )
    }
}