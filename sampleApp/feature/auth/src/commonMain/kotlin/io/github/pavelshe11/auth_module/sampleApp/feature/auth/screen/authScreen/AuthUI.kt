package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.authScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.pavelshe11.auth_module.sampleApp.common.utils.decompose.backAnimation
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen.ConfirmEmailUI
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen.LoginUI
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.registrationScreen.RegistrationUI

@Composable
fun AuthUI(
    component: AuthComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.subscribeAsState()

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {
                    Text("asdf")
                }
            )
        },
    ) { paddingScaffold ->
        Children(
            stack,
            modifier = modifier,
            animation = backAnimation(
                component.backHandler,
                component::onBackClicked
            )
        ) { child ->
            val childModifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(paddingScaffold)
                .background(MaterialTheme.colorScheme.background)

            when (val instance = child.instance) {
                is AuthComponent.Child.ConfirmEmail -> ConfirmEmailUI(instance.component, modifier = childModifier)
                is AuthComponent.Child.Registration -> RegistrationUI(instance.component, modifier = childModifier)
                is AuthComponent.Child.Login -> LoginUI(instance.component, modifier = childModifier)
            }
        }
    }
}