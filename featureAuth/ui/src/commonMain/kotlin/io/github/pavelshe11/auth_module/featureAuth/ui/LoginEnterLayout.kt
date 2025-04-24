package io.github.pavelshe11.auth_module.featureAuth.ui

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
import androidx.compose.ui.unit.dp

@Composable
fun LoginEnterLayout(
    title: String,
    subtitle: String,
    textFieldValue: String,
    textFieldLabel: String,
    textFieldPlaceholder: String,
    onChangedTextFieldValue: (String) -> Unit,
    buttonText: String,
    onClickButton: () -> Unit,
    enabledButton: Boolean,
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
            enabled = enabledButton,
        ) {
            Text(buttonText)
        }
    }
}