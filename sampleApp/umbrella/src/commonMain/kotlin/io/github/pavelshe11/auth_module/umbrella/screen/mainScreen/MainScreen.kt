package io.github.pavelshe11.auth_module.umbrella.screen.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    component: MainComponent,
    modifier: Modifier = Modifier
) {
    MainLayout(modifier = modifier)
}

@Composable
private fun MainLayout(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text("MainScreen", modifier = Modifier.align(Alignment.Center))
    }
}