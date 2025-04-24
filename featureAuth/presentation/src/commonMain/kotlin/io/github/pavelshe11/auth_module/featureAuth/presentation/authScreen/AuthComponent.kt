package io.github.pavelshe11.auth_module.featureAuth.presentation.authScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import io.github.pavelshe11.auth_module.featureAuth.presentation.confirmEmailScreen.ConfirmEmailComponent
import io.github.pavelshe11.auth_module.featureAuth.presentation.loginScreen.LoginComponent
import io.github.pavelshe11.auth_module.featureAuth.presentation.registrationScreen.RegistrationComponent
import kotlinx.serialization.Serializable

interface AuthComponent: BackHandlerOwner {

    val stack: Value<ChildStack<Config, Child>>

    @Serializable
    sealed class Config {
        @Serializable
        class Registration : Config()

        @Serializable
        class ConfirmEmail : Config()

        @Serializable
        class Login : Config()
    }

    sealed interface Child {
        class Registration(val component: RegistrationComponent) : Child

        class ConfirmEmail(val component: ConfirmEmailComponent) : Child

        class Login(val component: LoginComponent): Child
    }

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext, onConfirmedEmail: () -> Unit): AuthComponent
    }

    fun onBackClicked()

}