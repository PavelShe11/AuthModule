package io.github.pavelshe11.auth_module.featureAuth.presentation.loginScreen

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.value.Value

interface LoginComponent {

    val model: Value<Model>

    fun onChangeEmail(email: String)

    fun onClickSendCode()

    fun onSwitchRegistration()

    @Immutable
    data class Model(
        val email: String = "",
        val emailValid: Boolean = false,
    )

    fun interface Factory {
        operator fun invoke(
            onTransitionRegistration: () -> Unit,
            onSentCode: () -> Unit,
        ): LoginComponent
    }

}