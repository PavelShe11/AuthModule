package io.github.pavelshe11.auth_module.sampleApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import io.github.pavelshe11.auth_module.umbrella.root.DefaultRootComponent
import io.github.pavelshe11.auth_module.umbrella.root.RootUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = DefaultRootComponent.Factory().invoke(
            defaultComponentContext()
        )

        enableEdgeToEdge()
        setContent {
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !isSystemInDarkTheme()
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightNavigationBars = !isSystemInDarkTheme()
            RootUI(rootComponent)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {

}