package io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import io.github.auth_module.core.useCases.LoginUseCase
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore.*
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.LoginData
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.SendCodeConfirmUseCase

fun StoreFactory.authStore (
    sendCodeConfirmUseCase: SendCodeConfirmUseCase,
    loginUseCase: LoginUseCase<LoginData>,
): AuthStore = object : AuthStore, Store<Intent, State, Label> by create(
    name = "authStore",
    initialState = State.Default,
    executorFactory = {
        AuthExecutorImpl(
            sendCodeConfirmUseCase,
            loginUseCase
        )
    },
    reducer = AuthReducerImpl(),
) {}