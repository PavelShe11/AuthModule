package io.github.pavelshe11.auth_module.sampleApp.umbrella.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import io.github.pavelshe11.auth_module.featureAuth.presentation.authScreen.AuthComponent
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootComponent.Child
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootComponent.Config
import io.github.pavelshe11.auth_module.sampleApp.umbrella.screen.SplashScreen.SplashComponent
import io.github.pavelshe11.auth_module.sampleApp.umbrella.screen.mainScreen.MainComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ProdRootComponent internal constructor(
    context: ComponentContext,
//    private val getAuthStatus: GetAuthStatusUseCase,
    private val authComponentFactory: Lazy<AuthComponent.Factory>,
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
//            val config = when (getAuthStatus.getStatus()) {
//                GetAuthStatusUseCase.AuthStatus.NotAuthorized -> Config.Login()
//                else -> Config.Main()
//            }
//            stackNavigation.replaceAll(config)
        }
    }

    private fun createStack(
        config: Config,
        context: ComponentContext
    ) = when (config) {
        is Config.Splash -> Child.Splash(SplashComponent(context))
        is Config.Main -> Child.Main(MainComponent(context))
        is Config.Login -> Child.Login(authComponentFactory.value(context, {}))
    }

}

//@Prod
//@Factory([RootComponent.Factory::class])
class ProdRootComponentFactory(
//    private val getAuthStatusUseCase: GetAuthStatusUseCase,
    private val authComponentFactory: Lazy<AuthComponent.Factory>,
): RootComponent.Factory {

    override fun invoke(context: ComponentContext): RootComponent {
        return ProdRootComponent(
            context,
//            getAuthStatusUseCase,
            authComponentFactory
        )
    }

}