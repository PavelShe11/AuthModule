package io.github.pavelshe11.auth_module.featureAuth.core.domain

import io.github.pavelshe11.auth_module.sampleApp.common.exception.NetworkException
import kotlin.coroutines.cancellation.CancellationException

interface SendCodeConfirmUseCase {

    @Throws(
        NetworkException::class,
        CancellationException::class
    )
    suspend fun sendCodeConfirm(email: String)
}