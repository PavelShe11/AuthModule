package io.github.pavelshe11.auth_module.sample.screen.login.loginScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier
) {
    LoginLayout(
        modifier = modifier,
    )
}

@Composable
private fun LoginLayout(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {
                    Text("Вход")
                }
            )
        }
    ) {

    }
}