package io.github.pavelshe11.auth_module.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.pavelshe11.auth_module.sample.screen.SplashScreen.SplashScreen
import io.github.pavelshe11.auth_module.sample.screen.login.loginScreen.LoginScreen
import io.github.pavelshe11.auth_module.sample.screen.mainScreen.MainScreen
import io.github.pavelshe11.auth_module.uikit.theme.StudBridgeTheme

@Composable
fun RootUI(component: RootComponent) {
    val stack by component.stack.subscribeAsState()

    StudBridgeTheme {
        Children(
            stack = stack,
            modifier = Modifier
                .windowInsetsTopHeight(WindowInsets.systemBars)
                .windowInsetsBottomHeight(WindowInsets.navigationBars)
                .padding(16.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) { child ->
            val modifier = Modifier.fillMaxSize()

            when (val instance = child.instance) {
                is RootComponent.Child.Main -> MainScreen(instance.component, modifier)
                is RootComponent.Child.Login -> LoginScreen(instance.component, modifier)
                is RootComponent.Child.Splash -> SplashScreen(instance.component, modifier)
            }
        }
    }
}