package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen.ConfirmEmailScreen
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.enterEmailScreen.EnterEmailScreen

@Composable
fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.subscribeAsState()

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {
                    Text("")
                }
            )
        }
    ) {
        Children(
            stack,
            modifier = modifier
        ) { child ->
            val childModifier = Modifier.fillMaxSize()

            when (val instance = child.instance) {
                is LoginComponent.Child.ConfirmEmail -> ConfirmEmailScreen(instance.component, modifier = childModifier)
                is LoginComponent.Child.EnterEmail -> EnterEmailScreen(instance.component, modifier = childModifier)
            }
        }
    }
}

