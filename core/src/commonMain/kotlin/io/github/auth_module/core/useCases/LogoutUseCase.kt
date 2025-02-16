package io.github.auth_module.core.useCases

import io.github.auth_module.core.exception.LogoutException
import kotlin.coroutines.cancellation.CancellationException

interface LogoutUseCase {

    @Throws(
        LogoutException::class,
        CancellationException::class,
    )
    suspend fun logout()

}