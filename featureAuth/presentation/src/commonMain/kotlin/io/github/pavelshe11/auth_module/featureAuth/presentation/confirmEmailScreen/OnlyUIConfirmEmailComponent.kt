package io.github.pavelshe11.auth_module.featureAuth.presentation.confirmEmailScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import io.github.pavelshe11.auth_module.sampleApp.common.utils.koin.OnlyUI
import org.koin.core.annotation.Factory

class OnlyUIConfirmEmailComponent internal constructor(
    private val onConfirmedEmail: () -> Unit
): ConfirmEmailComponent {

    private val _model = MutableValue(ConfirmEmailComponent.Model())

    override val model: Value<ConfirmEmailComponent.Model> get() = _model

    override fun onChangeCode(code: String) {
        _model.value = _model.value.copy(code = code)
    }

    override fun onClickConfirmEmail() {
        onConfirmedEmail()
    }

}

@OnlyUI
@Factory([ConfirmEmailComponent.Factory::class])
class OnlyUIConfirmEmailComponentFactory(): ConfirmEmailComponent.Factory {

    override fun invoke(
        onConfirmedEmail: () -> Unit
    ): ConfirmEmailComponent {
        return OnlyUIConfirmEmailComponent(onConfirmedEmail)
    }

}