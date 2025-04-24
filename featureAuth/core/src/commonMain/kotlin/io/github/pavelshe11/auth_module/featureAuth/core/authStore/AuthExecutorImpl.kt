package io.github.pavelshe11.auth_module.featureAuth.core.authStore

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import io.github.auth_module.core.exception.LoginException
import io.github.auth_module.core.exception.NetworkException
import io.github.auth_module.core.useCases.LoginUseCase
import io.github.pavelshe11.auth_module.featureAuth.core.authStore.AuthExecutorImpl.Message
import io.github.pavelshe11.auth_module.featureAuth.core.authStore.AuthStore.*
import io.github.pavelshe11.auth_module.featureAuth.core.domain.LoginData
import io.github.pavelshe11.auth_module.featureAuth.core.domain.SendCodeConfirmUseCase
import io.github.pavelshe11.auth_module.sampleApp.common.utils.ifNotBlank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthExecutorImpl(
    private val sendCodeConfirmUseCase: SendCodeConfirmUseCase,
    private val loginUseCase: LoginUseCase<LoginData>
) : CoroutineExecutor<Intent, Any, State, Message, Label>() {

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.EmailChanged -> setPhoneNumber(intent.email)
            is Intent.SendCodeForConfirm -> sendCodeForConfirm()
            is Intent.CodeConfirmedChanged -> setCodeConfirmed(intent.code)
            is Intent.ConfirmEmail -> confirmEmail()
        }
    }

    private fun setPhoneNumber(phoneNumber: String) {
        dispatch(Message.EmailChanged(phoneNumber))
        dispatch(Message.EmailChangeValid(phoneNumber.length == 10))
    }

    private fun sendCodeForConfirm() {
        dispatch(Message.IsRefreshing(true))
        val email = state().email
        scope.launch(Dispatchers.Default) {
            try {
                sendCodeConfirmUseCase.sendCodeConfirm(email)
                publish(Label.EmailConfirm())
            } catch (e: NetworkException) {
                e.message?.ifNotBlank { publish(Label.Error(it)) }
            } finally {
                dispatch(Message.IsRefreshing(false))
            }
        }
    }

    private fun setCodeConfirmed(codeConfirmed: String) {
        dispatch(Message.CodeConfirmedChanged(codeConfirmed))
        dispatch(Message.CodeConfirmedChangeValid(codeConfirmed.length == 4))
    }

    private fun confirmEmail() {
        dispatch(Message.IsRefreshing(true))
        val email = state().email
        val code = state().codeConfirmed
        scope.launch(Dispatchers.Default) {
            try {
                loginUseCase.login(LoginData(email, code))
                publish(Label.EmailConfirmed())
            } catch (e: LoginException) {
                e.message?.ifNotBlank { publish(Label.Error(it)) }
            } finally {
                dispatch(Message.IsRefreshing(false))
            }
        }
    }

    sealed interface Message {
        data class IsRefreshing(val isRefreshing: Boolean) : Message
        data class EmailChanged(val email: String) : Message
        data class EmailChangeValid(val isValid: Boolean) : Message
        data class CodeConfirmedChanged(val code: String) : Message
        data class CodeConfirmedChangeValid(val isValid: Boolean) : Message
    }

}