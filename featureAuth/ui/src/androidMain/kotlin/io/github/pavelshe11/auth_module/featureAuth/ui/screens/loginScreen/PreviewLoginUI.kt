package io.github.pavelshe11.auth_module.featureAuth.ui.screens.loginScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.pavelshe11.auth_module.featureAuth.presentation.loginScreen.OnlyUILoginComponentFactory
import io.github.pavelshe11.auth_module.featureAuth.ui.screen.LoginUI
import io.github.pavelshe11.auth_module.uikit.theme.StudBridgeTheme

@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginUI() {
    StudBridgeTheme {
        LoginUI(
            OnlyUILoginComponentFactory().invoke(
                {},
                {}
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        )
    }
}