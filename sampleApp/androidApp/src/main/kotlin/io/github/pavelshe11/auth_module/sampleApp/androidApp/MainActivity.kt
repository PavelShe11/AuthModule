package io.github.pavelshe11.auth_module.sampleApp.androidApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import io.github.pavelshe11.auth_module.sampleApp.androidApp.preivew.PreviewScreen
import io.github.pavelshe11.auth_module.sampleApp.common.utils.koin.OnlyUI
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootComponent
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootUI
import io.github.pavelshe11.auth_module.uikit.theme.StudBridgeTheme
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {

    val rootComponentFactory: RootComponent.Factory by inject(named<OnlyUI>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = rootComponentFactory(defaultComponentContext())

        enableEdgeToEdge()

        setContent {
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !isSystemInDarkTheme()
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightNavigationBars = !isSystemInDarkTheme()
            RootUI(rootComponent)
        }
    }
}