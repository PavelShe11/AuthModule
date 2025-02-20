package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore

interface ConfirmEmailComponent {

    val model: Value<Model>

    fun onChangeCode(code: String)

    fun onClickConfirmEmail()

    @Immutable
    data class Model(
        val code: String,
        val codeValid: Boolean
    )

    fun interface Factory {
        operator fun invoke(
            store: AuthStore,
            context: ComponentContext
        ): ConfirmEmailComponent
    }

}