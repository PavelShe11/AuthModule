package io.github.pavelshe11.auth_module.featureAuth.presentation.confirmEmailScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.operator.map
import io.github.pavelshe11.auth_module.featureAuth.core.authStore.AuthStore
import io.github.pavelshe11.auth_module.sampleApp.common.utils.mvikotlin.asValue

class ProdConfirmEmailComponent internal constructor(
    context: ComponentContext,
    private val store: AuthStore
): ConfirmEmailComponent, ComponentContext by context {

    private val stateToModel: (AuthStore.State) -> ConfirmEmailComponent.Model = { state ->
        ConfirmEmailComponent.Model(
            state.codeConfirmed,
            state.codeConfirmedValid
        )
    }

    override val model get() = store.asValue().map(stateToModel)

    override fun onChangeCode(code: String) {
        store.accept(AuthStore.Intent.CodeConfirmedChanged(code))
    }

    override fun onClickConfirmEmail() {
        store.accept(AuthStore.Intent.ConfirmEmail())
    }

}

//@Prod
//@Factory([ConfirmEmailComponent.Factory::class])
//class ProdConfirmEmailComponentFactory(
//    private val store: AuthStore
//): ConfirmEmailComponent.Factory {
//
//    override fun invoke(
//        context: ComponentContext,
//        onConfirmedEmail: () -> Unit
//    ): ConfirmEmailComponent {
//        return ProdConfirmEmailComponent(context, store)
//    }
//
//}