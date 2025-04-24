package io.github.auth_module.core.useCases

import io.github.auth_module.core.exception.LoginException
import kotlin.coroutines.cancellation.CancellationException

interface LoginUseCase <LoginData> {

    @Throws(
        LoginException::class,
        CancellationException::class,
    )
    suspend fun login(data: LoginData)

}