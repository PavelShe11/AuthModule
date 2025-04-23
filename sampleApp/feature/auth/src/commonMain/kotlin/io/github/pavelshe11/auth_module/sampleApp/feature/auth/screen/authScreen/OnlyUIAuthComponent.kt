package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.authScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import io.github.pavelshe11.auth_module.sampleApp.common.utils.koin.OnlyUI
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.authScreen.AuthComponent.Child
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.authScreen.AuthComponent.Config
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen.ConfirmEmailComponent
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen.LoginComponent
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.registrationScreen.RegistrationComponent
import org.koin.core.annotation.Factory

class OnlyUIAuthComponent internal constructor(
    componentContext: ComponentContext,
    private val registrationComponentFactory: Lazy<RegistrationComponent.Factory>,
    private val confirmEmailComponentFactory: Lazy<ConfirmEmailComponent.Factory>,
    private val loginComponentFactory: Lazy<LoginComponent.Factory>,
    private val onFinishLogin: () -> Unit
) : AuthComponent, ComponentContext by componentContext {

    private val stackNavigation = StackNavigation<Config>()

    override val stack = childStack(
        source = stackNavigation,
        serializer = Config.serializer(),
        childFactory = ::createChild,
        handleBackButton = true,
        initialConfiguration = Config.Login()
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
                onFinishLogin
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

@OnlyUI
@Factory([AuthComponent.Factory::class])
class OnlyUILoginComponentFactory(
    @OnlyUI private val registrationComponentFactory: Lazy<RegistrationComponent.Factory>,
    @OnlyUI private val confirmEmailComponentFactory: Lazy<ConfirmEmailComponent.Factory>,
    @OnlyUI private val loginComponentFactory: Lazy<LoginComponent.Factory>,
) : AuthComponent.Factory {

    override fun invoke(componentContext: ComponentContext, onConfirmedEmail: () -> Unit): AuthComponent {
        return OnlyUIAuthComponent(
            componentContext,
            registrationComponentFactory,
            confirmEmailComponentFactory,
            loginComponentFactory,
            onConfirmedEmail
        )
    }

}