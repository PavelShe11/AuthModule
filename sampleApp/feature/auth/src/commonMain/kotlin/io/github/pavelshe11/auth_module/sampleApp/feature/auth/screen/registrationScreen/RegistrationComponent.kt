package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.registrationScreen

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.value.Value

interface RegistrationComponent {

    val model: Value<Model>

    fun onChangeFirstName(value: String)

    fun onChangeLastName(value: String)

    fun onChangeEmail(value: String)

    fun onChangePrivacyPolicy(value: Boolean)

    fun onChangeProcessingOfPersonalData(value: Boolean)

    fun onClickSendCode()

    @Immutable
    data class Model(
        val firstName: String = "",
        val lastName: String = "",
        val email: String = "",
        val emailValid: Boolean = false,
        val isPrivacyPolicy: Boolean = false,
        val isProcessingOfPersonalData: Boolean = false,
    )

    fun interface Factory {

        operator fun invoke(onSentCode: () -> Unit): RegistrationComponent
    }
}