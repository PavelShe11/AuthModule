package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.enterEmailScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.operator.map
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore
import io.github.pavelshe11.auth_module.sampleApp.utils.asValue

class DefaultEnterEmailComponent(
    context: ComponentContext,
    private val store: AuthStore,
): EnterEmailComponent, ComponentContext by context {

    private val stateToModel: (AuthStore.State) -> EnterEmailComponent.Model = {
        EnterEmailComponent.Model(
            it.email,
            it.emailValid
        )
    }

    override val model = store.asValue().map(stateToModel)

    override fun onChangeEmail(email: String) {
        store.accept(AuthStore.Intent.EmailChanged(email))
    }

    override fun onClickSendCode() {
        store.accept(AuthStore.Intent.ConfirmEmail())
    }

    @org.koin.core.annotation.Factory
    class Factory : EnterEmailComponent.Factory {
        override fun invoke(store: AuthStore, context: ComponentContext): EnterEmailComponent {
            return DefaultEnterEmailComponent(context, store)
        }
    }

}