package io.github.pavelshe11.auth_module.sample.screen.login.enterEmailScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import io.github.pavelshe11.auth_module.sample.screen.login.LoginEnterLayout
import io.github.pavelshe11.auth_module.sampleapp.generated.resources.*
import io.github.pavelshe11.auth_module.uikit.theme.textLinkStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun EnterEmailScreen(
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    var phoneNumberInput by remember { mutableStateOf(TextFieldValue("")) }

    val string = "Регистрируясь, вы принимаете политику конфиденциальности."
    val substring = "политику конфиденциальности."
    val startIndex = string.indexOf(substring)
    val endIndex = string.length - 1

    Column(modifier = modifier) {
        LoginEnterLayout(
            title = stringResource(Res.string.enter_email_screen_title),
            subtitle = stringResource(Res.string.enter_email_screen_subtitle),
            textFieldValue = phoneNumberInput,
            textFieldLabel = stringResource(Res.string.enter_email_screen_email_textfield_label),
            textFieldPlaceholder = stringResource(Res.string.enter_email_screen_email_textfield_placeholder),
            onChangedTextFieldValue = { phoneNumberInput = it },
            buttonText = stringResource(Res.string.enter_email_screen_confirm_email_button),
            onClickButton = {},
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 8.dp)
        )

        Text(
            buildAnnotatedString {
                append(string)
                addLink(
                    url = LinkAnnotation.Url(
                        "https://pavelshe11.github.io/",
                        MaterialTheme.textLinkStyles
                    ),
                    startIndex,
                    endIndex
                )
            },
            color = colors.onBackground,
            style = typography.bodyMedium,
            modifier = Modifier.clickable {

            }
        )
    }


}

