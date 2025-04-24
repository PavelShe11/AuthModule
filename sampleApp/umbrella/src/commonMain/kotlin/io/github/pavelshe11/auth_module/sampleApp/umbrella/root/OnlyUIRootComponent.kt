package io.github.pavelshe11.auth_module.sampleApp.umbrella.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import io.github.pavelshe11.auth_module.featureAuth.presentation.authScreen.AuthComponent
import io.github.pavelshe11.auth_module.sampleApp.common.utils.koin.OnlyUI
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootComponent.Child
import io.github.pavelshe11.auth_module.sampleApp.umbrella.root.RootComponent.Config
import io.github.pavelshe11.auth_module.sampleApp.umbrella.screen.SplashScreen.SplashComponent
import io.github.pavelshe11.auth_module.sampleApp.umbrella.screen.mainScreen.MainComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

class OnlyUIRootComponent internal constructor(
    context: ComponentContext,
    private val authComponentFactory: Lazy<AuthComponent.Factory>,
) : RootComponent, ComponentContext by context {

    private val coroutineScope = context.coroutineScope(Dispatchers.Main)

    private val stackNavigation = StackNavigation<Config>()

    override val stack = childStack(
        source = stackNavigation,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createStack,
        initialConfiguration = Config.Splash()
    )

    init {
        coroutineScope.launch {
            delay(1000)
            stackNavigation.replaceAll(Config.Login())
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

@OnlyUI
@Factory([RootComponent.Factory::class])
class OnlyUIRootComponentFactory(
    @OnlyUI private val authComponentFactory: Lazy<AuthComponent.Factory>
): RootComponent.Factory {

    override fun invoke(context: ComponentContext): RootComponent {
        return OnlyUIRootComponent(
            context,
            authComponentFactory
        )
    }

}