package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen.ConfirmEmailComponent
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.enterEmailScreen.EnterEmailComponent
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen.LoginComponent.Child
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen.LoginComponent.Config

class DefaultLoginComponent(
    context: ComponentContext,
    store: AuthStore,
    private val enterEmailComponentFactory: Lazy<EnterEmailComponent.Factory>,
    private val confirmEmailComponentFactory: Lazy<ConfirmEmailComponent.Factory>
) : LoginComponent, ComponentContext by context {

    private val stackNavigation = StackNavigation<Config>()

    override val stack = childStack(
        source = stackNavigation,
        serializer = Config.serializer(),
        childFactory = ::createChild,
        initialConfiguration = Config.EnterEmail()
    )

    private val store = instanceKeeper.getStore { store }

    private fun createChild(
        config: Config,
        context: ComponentContext
    ) = when (config) {
        is Config.EnterEmail -> Child.EnterEmail(
            enterEmailComponentFactory.value(
                store,
                context
            )
        )
        is Config.ConfirmEmail -> Child.ConfirmEmail(
            confirmEmailComponentFactory.value(
                store,
                context
            )
        )
    }

    @org.koin.core.annotation.Factory
    class Factory(
        private val store: AuthStore,
        private val enterEmailComponentFactory: Lazy<EnterEmailComponent.Factory>,
        private val confirmEmailComponentFactory: Lazy<ConfirmEmailComponent.Factory>
    ): LoginComponent.Factory {

        override fun invoke(componentContext: ComponentContext): LoginComponent {
            return DefaultLoginComponent(
                context = componentContext,
                store,
                enterEmailComponentFactory,
                confirmEmailComponentFactory
            )
        }

    }

}