package io.github.pavelshe11.auth_module.featureAuth.core.authStore

import com.arkivanov.mvikotlin.core.store.Reducer
import io.github.pavelshe11.auth_module.featureAuth.core.authStore.AuthExecutorImpl.Message
import io.github.pavelshe11.auth_module.featureAuth.core.authStore.AuthStore.State

class AuthReducerImpl: Reducer<State, Message> {

    override fun State.reduce(msg: Message): State {
        return when (msg) {
            is Message.CodeConfirmedChangeValid -> copy(codeConfirmedValid = msg.isValid)
            is Message.CodeConfirmedChanged -> copy(codeConfirmed = msg.code)
            is Message.EmailChangeValid -> copy(emailValid = msg.isValid)
            is Message.EmailChanged -> copy(email = msg.email)
            is Message.IsRefreshing -> copy(isProcessing = msg.isRefreshing)
        }
    }

}