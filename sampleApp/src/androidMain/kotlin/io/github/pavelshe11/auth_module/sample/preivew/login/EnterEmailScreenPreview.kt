package io.github.pavelshe11.auth_module.sample.preivew.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.pavelshe11.auth_module.sample.preivew.PreviewScreen
import io.github.pavelshe11.auth_module.sample.screen.login.enterEmailScreen.EnterEmailScreen
import io.github.pavelshe11.auth_module.uikit.theme.StudBridgeTheme

@PreviewScreen
@Composable
fun EnterEmailScreenPreview() {
    StudBridgeTheme {
        EnterEmailScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        )
    }
}