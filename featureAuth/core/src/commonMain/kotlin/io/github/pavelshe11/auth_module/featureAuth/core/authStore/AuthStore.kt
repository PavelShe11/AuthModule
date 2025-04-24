package io.github.pavelshe11.auth_module.featureAuth.core.authStore

import com.arkivanov.mvikotlin.core.store.Store
import io.github.pavelshe11.auth_module.featureAuth.core.authStore.AuthStore.Intent
import io.github.pavelshe11.auth_module.featureAuth.core.authStore.AuthStore.State
import kotlinx.serialization.Serializable

interface AuthStore: Store<Intent, State, AuthStore.Label> {

    sealed interface Intent {
        data class EmailChanged(val email: String) : Intent
        data class CodeConfirmedChanged(val code: String) : Intent
        class SendCodeForConfirm: Intent
        class ConfirmEmail: Intent
    }

    @Serializable
    data class State(
        val isProcessing: Boolean,
        val email: String,
        val codeConfirmed: String,
        val emailValid: Boolean,
        val codeConfirmedValid: Boolean,
    ) {
        companion object {
            val Default = State(
                isProcessing = false,
                email = "",
                codeConfirmed = "",
                emailValid = false,
                codeConfirmedValid = false,
            )
        }
    }

    sealed interface Label {
        class Error(val error: String): Label
        class EmailConfirm: Label
        class EmailConfirmed: Label
    }

}