package io.github.pavelshe11.auth_module.sampleApp.feature.auth

import com.arkivanov.mvikotlin.core.store.StoreFactory
import io.github.auth_module.core.Auth
import io.github.auth_module.core.AuthManager
import io.github.auth_module.core.useCases.LoginUseCase
import io.github.auth_module.oauth_2_0.impl.DefaultOAuth2Engine
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.AuthStore
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.authStore.authStore
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.LoginData
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.SendCodeConfirmUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class AuthDiModule {

    fun authManager(): AuthManager<LoginData> {
        val auth = Auth(DefaultOAuth2Engine<LoginData>()).init {
            this.engine {
                this.
            }
        }
    }

    @Factory
    fun authStore(
        storeFactory: StoreFactory,
        sendCodeConfirmUseCase: SendCodeConfirmUseCase,
        loginUseCase: LoginUseCase<LoginData>
    ): AuthStore {
        return storeFactory.authStore(sendCodeConfirmUseCase, loginUseCase)
    }

}