package io.github.pavelshe11.auth_module.umbrella.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import io.github.auth_module.core.useCases.GetAuthStatusUseCase
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen.LoginComponent
import io.github.pavelshe11.auth_module.umbrella.root.RootComponent.Child
import io.github.pavelshe11.auth_module.umbrella.root.RootComponent.Config
import io.github.pavelshe11.auth_module.umbrella.screen.SplashScreen.SplashComponent
import io.github.pavelshe11.auth_module.umbrella.screen.mainScreen.MainComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DefaultRootComponent(
    private val getAuthStatus: GetAuthStatusUseCase,
    private val loginComponentFactory: Lazy<LoginComponent.Factory>,
    context: ComponentContext,
) : RootComponent, ComponentContext by context {

    private val coroutineScope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val stackNavigation = StackNavigation<Config>()

    override val stack = childStack(
        source = stackNavigation,
        serializer = Config.serializer(),
        childFactory = ::createStack,
        initialConfiguration = Config.Splash()
    )

    init {
        coroutineScope.launch {
            val config = when (getAuthStatus.getStatus()) {
                GetAuthStatusUseCase.AuthStatus.NotAuthorized -> Config.Login()
                else -> Config.Main()
            }
            stackNavigation.replaceAll(config)
        }
    }

    private fun createStack(
        config: Config,
        context: ComponentContext
    ) = when (config) {
        is Config.Splash -> Child.Splash(SplashComponent(context))
        is Config.Main -> Child.Main(MainComponent(context))
        is Config.Login -> Child.Login(loginComponentFactory.value(context))
    }

    @org.koin.core.annotation.Factory
    class Factory(
        private val getAuthStatusUseCase: GetAuthStatusUseCase,
        private val loginComponentFactory: Lazy<LoginComponent.Factory>,
    ): RootComponent.Factory {

        override fun invoke(context: ComponentContext): RootComponent {
            return DefaultRootComponent(
                getAuthStatusUseCase,
                loginComponentFactory,
                context
            )
        }

    }

}