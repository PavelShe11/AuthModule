package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.LoginEnterLayout
import io.github.pavelshe11.auth_module.sampleapp.feature.auth.auth.generated.resources.Res
import io.github.pavelshe11.auth_module.sampleapp.feature.auth.auth.generated.resources.enter_confirm_email_screen_confirmed_email_button
import io.github.pavelshe11.auth_module.sampleapp.feature.auth.auth.generated.resources.enter_confirm_email_screen_confirmed_email_textfield_label
import io.github.pavelshe11.auth_module.sampleapp.feature.auth.auth.generated.resources.enter_confirm_email_screen_confirmed_email_textfield_placeholder
import io.github.pavelshe11.auth_module.sampleapp.feature.auth.auth.generated.resources.enter_confirm_email_screen_subtitle
import io.github.pavelshe11.auth_module.sampleapp.feature.auth.auth.generated.resources.enter_confirm_email_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfirmEmailScreen(
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
        modifier = modifier,
    )
}