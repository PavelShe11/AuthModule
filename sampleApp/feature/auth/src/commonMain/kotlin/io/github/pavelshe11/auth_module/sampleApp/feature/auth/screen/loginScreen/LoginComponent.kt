package io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.loginScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.confirmEmailScreen.ConfirmEmailComponent
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.screen.enterEmailScreen.EnterEmailComponent
import kotlinx.serialization.Serializable

interface LoginComponent {

    val stack: Value<ChildStack<Config, Child>>

    @Serializable
    sealed class Config {
        @Serializable
        class EnterEmail : Config()
        @Serializable
        class ConfirmEmail : Config()

    }

    sealed interface Child {
        class EnterEmail(val component: EnterEmailComponent) : Child
        class ConfirmEmail(val component: ConfirmEmailComponent) : Child
    }

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): LoginComponent
    }

}