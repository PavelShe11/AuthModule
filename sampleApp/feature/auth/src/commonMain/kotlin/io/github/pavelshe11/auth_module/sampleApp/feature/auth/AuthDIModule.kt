package io.github.pavelshe11.auth_module.sampleApp.feature.auth

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("io.github.pavelshe11.auth_module.sampleApp.feature.auth")
class AuthDIModule {

//    @Factory
//    fun authStore(
//        storeFactory: StoreFactory,
//        sendCodeConfirmUseCase: SendCodeConfirmUseCase,
//        loginUseCase: LoginUseCase<LoginData>
//    ): AuthStore {
//        return storeFactory.authStore(sendCodeConfirmUseCase, loginUseCase)
//    }

}