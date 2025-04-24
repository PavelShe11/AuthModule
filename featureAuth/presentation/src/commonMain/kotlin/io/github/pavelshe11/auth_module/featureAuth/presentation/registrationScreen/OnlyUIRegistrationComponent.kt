package io.github.pavelshe11.auth_module.featureAuth.presentation.registrationScreen

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import io.github.pavelshe11.auth_module.sampleApp.common.utils.koin.OnlyUI
import org.koin.core.annotation.Factory

class OnlyUIRegistrationComponent internal constructor(
    private val onSentCode: () -> Unit,
): RegistrationComponent {

    private val _model = MutableValue(RegistrationComponent.Model(emailValid = true))

    override val model: Value<RegistrationComponent.Model> get() = _model

    override fun onChangeFirstName(value: String) {
        _model.value = _model.value.copy(firstName = value)
    }

    override fun onChangeLastName(value: String) {
        _model.value = _model.value.copy(lastName = value)
    }

    override fun onChangeEmail(value: String) {
        _model.value = _model.value.copy(email = value)
    }

    override fun onChangePrivacyPolicy(value: Boolean) {
        _model.value = _model.value.copy(isPrivacyPolicy = value)
    }

    override fun onChangeProcessingOfPersonalData(value: Boolean) {
        _model.value = _model.value.copy(isProcessingOfPersonalData = value)
    }

    override fun onClickSendCode() {
        onSentCode()
    }
}

@OnlyUI
@Factory([RegistrationComponent.Factory::class])
class OnlyUIRegistrationComponentFactory(): RegistrationComponent.Factory {

    override fun invoke(
        onSentCode: () -> Unit
    ): RegistrationComponent {
        return OnlyUIRegistrationComponent(
            onSentCode
        )
    }

}