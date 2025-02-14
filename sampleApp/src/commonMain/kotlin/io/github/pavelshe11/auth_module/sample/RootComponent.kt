package io.github.pavelshe11.auth_module.sample

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import io.github.pavelshe11.auth_module.sample.screen.SplashScreen.SplashComponent
import io.github.pavelshe11.auth_module.sample.screen.login.loginScreen.LoginComponent
import io.github.pavelshe11.auth_module.sample.screen.mainScreen.MainComponent
import kotlinx.serialization.Serializable

class RootComponent(
    context: ComponentContext,
): ComponentContext by context {

    private val stackNavigation = StackNavigation<Config>()
    val stack = childStack(
        source = stackNavigation,
        serializer = Config.serializer(),
        childFactory = ::createStack,
        initialConfiguration = Config.Splash()
    )

    private val slotNavigator = SlotNavigation<SlotConfig>()
    val slot = childSlot(
        slot = slotNavigator,
        SlotConfig.serializer(),
        backHandler = true,
        {}
    )

    private fun createStack(
        config: Config,
        context: ComponentContext
    ) = when(config) {
        is Config.Splash -> Child.Splash(SplashComponent(context))
        is Config.Main -> Child.Main(MainComponent(context))
        is Config.Login -> Child.Login(LoginComponent(context))
    }

    @Serializable
    sealed interface Config {
        @Serializable
        class Splash: Config
        @Serializable
        class Main: Config
        @Serializable
        class Login: Config
    }

    @Serializable
    data class SlotConfig(val message: String)

    sealed interface Child {
        class Splash(val component: SplashComponent): Child
        class Main(val component: MainComponent): Child
        class Login(val component: LoginComponent): Child
    }

}