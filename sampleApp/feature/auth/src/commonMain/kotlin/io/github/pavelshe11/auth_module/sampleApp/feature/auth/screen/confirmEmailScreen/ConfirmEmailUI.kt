package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.pavelshe11.auth_module.auth.generated.resources.*
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.LoginEnterLayout
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfirmEmailUI(
    component: ConfirmEmailComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.model.subscribeAsState()

    LoginEnterLayout(
        title = stringResource(Res.string.enter_confirm_email_screen_title),
        subtitle = stringResource(Res.string.enter_confirm_email_screen_subtitle),
        textFieldValue = state.code,
        textFieldLabel = stringResource(Res.string.enter_confirm_email_screen_confirmed_email_textfield_label),
        textFieldPlaceholder = stringResource(Res.string.enter_confirm_email_screen_confirmed_email_textfield_placeholder),
        onChangedTextFieldValue = component::onChangeCode,
        buttonText = stringResource(Res.string.enter_confirm_email_screen_confirmed_email_button),
        onClickButton = component::onClickConfirmEmail,
        enabledButton = state.codeValid,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp),
    )
}