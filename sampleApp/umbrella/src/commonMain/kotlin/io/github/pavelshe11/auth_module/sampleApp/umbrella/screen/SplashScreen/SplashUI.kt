package io.github.pavelshe11.auth_module.sampleApp.umbrella.screen.SplashScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen(
    component: SplashComponent,
    modifier: Modifier = Modifier
) {
    SplashLayout(modifier)
}

@Composable
private fun SplashLayout(modifier: Modifier = Modifier) {
    Text("SplashLayout", color = MaterialTheme.colorScheme.onBackground)
}