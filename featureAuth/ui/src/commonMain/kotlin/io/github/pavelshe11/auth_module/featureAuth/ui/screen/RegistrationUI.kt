package io.github.pavelshe11.auth_module.featureAuth.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.pavelshe11.auth_module.featureAuth.presentation.registrationScreen.RegistrationComponent
import io.github.pavelshe11.auth_module.sampleApp.common.utils.compose.substringUrlAnnotatedString

@Composable
fun RegistrationUI(
    component: RegistrationComponent,
    modifier: Modifier = Modifier
) {
    val state by component.model.subscribeAsState()

    val focusRequester = remember { FocusRequester() }

    val typography = MaterialTheme.typography

    val privacyPolicyText = "Регистрируясь, вы принимаете политику конфиденциальности."
    val privacyPolicy = "политику конфиденциальности."

    val processingOfPersonalDataText = "Регистрируясь, вы принимаете соглашение на обработку персональных данных."
    val processingOfPersonalData = "соглашение на обработку персональных данных"

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp)
    ) {

        OutlinedTextField(
            value = state.firstName,
            onValueChange = component::onChangeFirstName,
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
        )

        OutlinedTextField(
            value = state.lastName,
            component::onChangeLastName,
            label = { Text("Фамилия") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = state.email,
            component::onChangeEmail,
            label = { Text("Электронная почта") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        )

        Spacer(Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                substringUrlAnnotatedString(
                    privacyPolicyText,
                    privacyPolicy,
                    "https://pavelshe11.github.io/"
                ),
                style = typography.bodyMedium,
                modifier = Modifier.weight(1f),
            )

            Checkbox(
                checked = state.isPrivacyPolicy,
                onCheckedChange = component::onChangePrivacyPolicy,
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                substringUrlAnnotatedString(
                    processingOfPersonalDataText,
                    processingOfPersonalData,
                    "https://pavelshe11.github.io/"
                ),
                style = typography.bodyMedium,
                modifier = Modifier.weight(1f).padding(top = 4.dp),
            )

            Checkbox(
                checked = state.isProcessingOfPersonalData,
                onCheckedChange = component::onChangeProcessingOfPersonalData,
                modifier = Modifier.fillMaxHeight()
            )
        }

        Button(
            component::onClickSendCode,
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 16.dp),
            enabled = state.emailValid,
        ) {
            Text("Зарегистрироваться")
        }
    }


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

}