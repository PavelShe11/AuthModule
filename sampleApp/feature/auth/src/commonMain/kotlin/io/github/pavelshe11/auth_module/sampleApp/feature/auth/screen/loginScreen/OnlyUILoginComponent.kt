package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import io.github.pavelshe11.auth_module.sampleApp.common.utils.koin.OnlyUI
import org.koin.core.annotation.Factory

class OnlyUILoginComponent internal constructor(
    private val onTransitionRegistration: () -> Unit,
    private val onSentCode: () -> Unit,
): LoginComponent {

    private val _model = MutableValue(LoginComponent.Model(emailValid = true))

    override val model: Value<LoginComponent.Model> get() = _model

    override fun onChangeEmail(email: String) {
        _model.value = _model.value.copy(email = email)
    }

    override fun onClickSendCode() {
        onSentCode()
    }

    override fun onSwitchRegistration() {
        onTransitionRegistration()
    }

}

@OnlyUI
@Factory([LoginComponent.Factory::class])
class OnlyUILoginComponentFactory() : LoginComponent.Factory {

    override fun invoke(
        onTransitionRegistration: () -> Unit,
        onSentCode: () -> Unit
    ): LoginComponent {
        return OnlyUILoginComponent(onTransitionRegistration, onSentCode,)
    }

}