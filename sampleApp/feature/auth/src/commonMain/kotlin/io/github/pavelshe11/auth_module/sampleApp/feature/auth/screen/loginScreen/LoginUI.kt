package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun LoginUI(component: LoginComponent, modifier: Modifier = Modifier) {
    val state by component.model.subscribeAsState()

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp)
    ) {

        OutlinedTextField(
            state.email,
            component::onChangeEmail,
            label = { Text(text = "Email") },
            placeholder = { Text(text = "my.example@mail.ru") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .focusRequester(focusRequester)
        )

        Spacer(Modifier.weight(1f))

        Button(component::onSwitchRegistration) {
            Text(
                "Регистрация",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }

        Button(
            component::onClickSendCode,
            enabled = state.emailValid,
        ) {
            Text(
                "Подтвердить почту",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}