package io.github.pavelshe11.auth_module.featureAuth.presentation.confirmEmailScreen

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.value.Value

interface ConfirmEmailComponent {

    val model: Value<Model>

    fun onChangeCode(code: String)

    fun onClickConfirmEmail()

    @Immutable
    data class Model(
        val code: String = "",
        val codeValid: Boolean = false
    )

    fun interface Factory {
        
        operator fun invoke(onConfirmedEmail: () -> Unit): ConfirmEmailComponent
    }

}