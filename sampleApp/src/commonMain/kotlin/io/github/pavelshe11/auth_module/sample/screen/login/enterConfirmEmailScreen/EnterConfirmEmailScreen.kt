package io.github.pavelshe11.auth_module.sample.screen.login.enterConfirmEmailScreen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import io.github.pavelshe11.auth_module.sample.screen.login.LoginEnterLayout
import io.github.pavelshe11.auth_module.sampleapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun EnterConfirmEmailScreen(modifier: Modifier = Modifier) {
    var confirmEmail by remember { mutableStateOf(TextFieldValue("")) }

    LoginEnterLayout(
        title = stringResource(Res.string.enter_confirm_email_screen_title),
        subtitle = stringResource(Res.string.enter_confirm_email_screen_subtitle),
        textFieldValue = confirmEmail,
        textFieldLabel = stringResource(Res.string.enter_confirm_email_screen_confirmed_email_textfield_label),
        textFieldPlaceholder = stringResource(Res.string.enter_confirm_email_screen_confirmed_email_textfield_placeholder),
        onChangedTextFieldValue = { confirmEmail = it },
        buttonText = stringResource(Res.string.enter_confirm_email_screen_confirmed_email_button),
        onClickButton = { },
        modifier = modifier,
    )
}