package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.enterEmailScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.LoginEnterLayout
import io.github.pavelshe11.auth_module.sampleapp.feature.auth.auth.generated.resources.*
import io.github.pavelshe11.auth_module.uikit.theme.textLinkStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun EnterEmailScreen(
    component: EnterEmailComponent,
    modifier: Modifier = Modifier
) {
    val state by component.model.subscribeAsState()

    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val string = "Регистрируясь, вы принимаете политику конфиденциальности."
    val substring = "политику конфиденциальности."
    val startIndex = string.indexOf(substring)
    val endIndex = string.length - 1

    Column(modifier = modifier) {
        LoginEnterLayout(
            title = stringResource(Res.string.enter_email_screen_title),
            subtitle = stringResource(Res.string.enter_email_screen_subtitle),
            textFieldValue = state.email,
            textFieldLabel = stringResource(Res.string.enter_email_screen_email_textfield_label),
            textFieldPlaceholder = stringResource(Res.string.enter_email_screen_email_textfield_placeholder),
            onChangedTextFieldValue = component::onChangeEmail,
            buttonText = stringResource(Res.string.enter_email_screen_confirm_email_button),
            onClickButton = component::onClickSendCode,
            enabledButton = state.emailValid,
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

