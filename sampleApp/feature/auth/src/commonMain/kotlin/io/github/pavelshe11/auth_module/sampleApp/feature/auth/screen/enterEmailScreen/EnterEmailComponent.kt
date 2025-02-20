package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.enterEmailScreen

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore

interface EnterEmailComponent {

    val model: Value<Model>

    fun onChangeEmail(email: String)

    fun onClickSendCode()

    @Immutable
    data class Model(
        val email: String,
        val emailValid: Boolean
    )

    fun interface Factory {
        operator fun invoke(
            store: AuthStore,
            context: ComponentContext
        ): EnterEmailComponent
    }
}