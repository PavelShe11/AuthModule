package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.operator.map
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore
import io.github.pavelshe11.auth_module.sampleApp.utils.asValue

class DefaultConfirmEmailComponent(
    context: ComponentContext,
    private val store: AuthStore
): ConfirmEmailComponent, ComponentContext by context {

    private val stateToModel: (AuthStore.State) -> ConfirmEmailComponent.Model = { state ->
        ConfirmEmailComponent.Model(
            state.codeConfirmed,
            state.codeConfirmedValid
        )
    }

    override val model = store.asValue().map(stateToModel)

    override fun onChangeCode(code: String) {
        store.accept(AuthStore.Intent.CodeConfirmedChanged(code))
    }

    override fun onClickConfirmEmail() {
        store.accept(AuthStore.Intent.ConfirmEmail())
    }

    @org.koin.core.annotation.Factory
    class Factory: ConfirmEmailComponent.Factory {
        override fun invoke(store: AuthStore, context: ComponentContext): ConfirmEmailComponent {
            return DefaultConfirmEmailComponent(context, store)
        }
    }

}