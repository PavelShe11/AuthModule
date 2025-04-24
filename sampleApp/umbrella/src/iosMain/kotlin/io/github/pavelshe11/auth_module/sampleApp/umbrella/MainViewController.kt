package io.github.pavelshe11.auth_module.sampleApp

import androidx.compose.ui.window.ComposeUIViewController
import io.github.pavelshe11.auth_module.uikit.theme.StudBridgeTheme
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootComponent
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootUI

fun MainViewController(root: RootComponent) = ComposeUIViewController {
    StudBridgeTheme {
        RootUI(root)
    }
}

