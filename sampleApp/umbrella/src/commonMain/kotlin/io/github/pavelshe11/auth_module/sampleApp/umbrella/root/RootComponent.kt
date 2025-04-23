package io.github.pavelshe11.auth_module.sampleApp.umbrella.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.authScreen.AuthComponent
import io.github.pavelshe11.auth_module.sampleApp.umbrella.screen.SplashScreen.SplashComponent
import io.github.pavelshe11.auth_module.sampleApp.umbrella.screen.mainScreen.MainComponent
import kotlinx.serialization.Serializable

interface RootComponent {

    val stack: Value<ChildStack<Config, Child>>

    @Serializable
    sealed interface Config {
        @Serializable
        class Splash : Config

        @Serializable
        class Main : Config

        @Serializable
        class Login : Config
    }

    sealed interface Child {
        class Splash(val component: SplashComponent) : Child
        class Main(val component: MainComponent) : Child
        class Login(val component: AuthComponent) : Child
    }

    fun interface Factory {
        operator fun invoke(context: ComponentContext): RootComponent
    }
}