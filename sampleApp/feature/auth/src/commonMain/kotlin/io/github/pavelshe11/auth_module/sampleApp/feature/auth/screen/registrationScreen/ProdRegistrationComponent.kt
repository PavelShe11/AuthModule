package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.registrationScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.operator.map
import io.github.pavelshe11.auth_module.sampleApp.common.utils.koin.Prod
import io.github.pavelshe11.auth_module.sampleApp.common.utils.kstor.asValue
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore
import org.koin.core.annotation.Factory

//class ProdRegistrationComponent internal constructor(
//    context: ComponentContext,
//    private val store: AuthStore,
//) : RegistrationComponent, ComponentContext by context {
//
//    private val stateToModel: (AuthStore.State) -> RegistrationComponent.Model = {
//        RegistrationComponent.Model(
//            it.email,
//            it.emailValid
//        )
//    }
//
//    override val model = store.asValue().map(stateToModel)
//
//    override fun onChangeEmail(email: String) {
//        store.accept(AuthStore.Intent.EmailChanged(email))
//    }
//
//    override fun onClickSendCode() {
//        store.accept(AuthStore.Intent.ConfirmEmail())
//    }
//
//}

//@Prod
//@Factory([RegistrationComponent.Factory::class])
//class ProdRegistrationComponentFactory(
//    private val store: AuthStore,
//) : RegistrationComponent.Factory {
//
//    override fun invoke(
//        context: ComponentContext,
//        onSentCode: () -> Unit
//    ): RegistrationComponent {
//        return ProdRegistrationComponent(context, store)
//    }
//
//}