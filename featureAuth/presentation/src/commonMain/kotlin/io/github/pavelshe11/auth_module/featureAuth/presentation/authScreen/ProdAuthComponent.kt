package io.github.pavelshe11.auth_module.featureAuth.presentation.authScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import io.github.pavelshe11.auth_module.featureAuth.presentation.authScreen.AuthComponent.Child
import io.github.pavelshe11.auth_module.featureAuth.presentation.authScreen.AuthComponent.Config
import io.github.pavelshe11.auth_module.featureAuth.presentation.confirmEmailScreen.ConfirmEmailComponent
import io.github.pavelshe11.auth_module.featureAuth.presentation.loginScreen.LoginComponent
import io.github.pavelshe11.auth_module.featureAuth.presentation.registrationScreen.RegistrationComponent

class ProdAuthComponent(
    context: ComponentContext,
    private val registrationComponentFactory: Lazy<RegistrationComponent.Factory>,
    private val confirmEmailComponentFactory: Lazy<ConfirmEmailComponent.Factory>,
    private val loginComponentFactory: Lazy<LoginComponent.Factory>,
    private val onConfirmedEmail: () -> Unit
) : AuthComponent, ComponentContext by context {

    private val stackNavigation = StackNavigation<Config>()

    override val stack = childStack(
        source = stackNavigation,
        serializer = Config.serializer(),
        childFactory = ::createChild,
        initialConfiguration = Config.Registration()
    )

    override fun onBackClicked() {
        stackNavigation.pop()
    }

    private fun createChild(
        config: Config,
        context: ComponentContext
    ) = when (config) {
        is Config.Registration -> Child.Registration(
            registrationComponentFactory.value(
                ::onSentCode
            )
        )

        is Config.ConfirmEmail -> Child.ConfirmEmail(
            confirmEmailComponentFactory.value(
                onConfirmedEmail
            )
        )

        is Config.Login -> Child.Login(
            loginComponentFactory.value(
                ::onTransitionRegistration,
                ::onSentCode
            )
        )
    }

    private fun onTransitionRegistration() {
        stackNavigation.pushNew(Config.Registration())
    }

    private fun onSentCode() {
        stackNavigation.pushNew(Config.ConfirmEmail())
    }

}

//@Prod
//@Factory([AuthComponent.Factory::class])
//class ProdLoginComponentFactory(
//    private val enterEmailComponentFactory: Lazy<RegistrationComponent.Factory>,
//    private val confirmEmailComponentFactory: Lazy<ConfirmEmailComponent.Factory>
//): AuthComponent.Factory {
//
//    override fun invoke(
//        componentContext: ComponentContext,
//        onConfirmedEmail: () -> Unit
//    ): AuthComponent {
//        return ProdAuthComponent(
//            context = componentContext,
//            enterEmailComponentFactory,
//            confirmEmailComponentFactory,
//            onConfirmedEmail
//        )
//    }
//
//}