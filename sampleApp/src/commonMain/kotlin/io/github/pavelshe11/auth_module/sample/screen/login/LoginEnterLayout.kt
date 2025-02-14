package io.github.pavelshe11.auth_module.sample.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun LoginEnterLayout(
    title: String,
    subtitle: String,
    textFieldValue: TextFieldValue,
    textFieldLabel: String,
    textFieldPlaceholder: String,
    onChangedTextFieldValue: (TextFieldValue) -> Unit,
    buttonText: String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val typography = MaterialTheme.typography
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = modifier
    ) {
        Text(
            title,
            style = typography.displayMedium,
            color = colors.onBackground,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        Text(
            subtitle,
            style = typography.bodyMedium,
            color = colors.onBackground.copy(alpha = 0.8f),
            modifier = Modifier.padding(bottom = 32.dp),
        )

        OutlinedTextField(
            textFieldValue,
            onChangedTextFieldValue,
            label = { Text(textFieldLabel) },
            placeholder = { Text(textFieldPlaceholder) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onClickButton,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(buttonText)
        }
    }
}