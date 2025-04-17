package io.github.pavelshe11.auth_module.sampleApp.feature.auth

import com.arkivanov.mvikotlin.core.store.StoreFactory
import io.github.auth_module.core.useCases.LoginUseCase
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.authStore
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.LoginData
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.SendCodeConfirmUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class AuthDIModule {

    @Factory
    fun authStore(
        storeFactory: StoreFactory,
        sendCodeConfirmUseCase: SendCodeConfirmUseCase,
        loginUseCase: LoginUseCase<LoginData>
    ): AuthStore {
        return storeFactory.authStore(sendCodeConfirmUseCase, loginUseCase)
    }

}