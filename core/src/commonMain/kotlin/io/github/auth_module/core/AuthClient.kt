package io.github.auth_module.core

import io.github.auth_module.core.exception.LoginException
import io.github.auth_module.core.exception.LogoutException
import io.github.auth_module.core.exception.NetworkException
import kotlin.coroutines.cancellation.CancellationException

interface AuthClient<in LoginData, out LoginResponse, out LogoutResponse> {

    @Throws(
        LoginException::class,
        NetworkException::class,
        CancellationException::class,
    )
    suspend fun login(data: LoginData): LoginResponse

    @Throws(
        LogoutException::class,
        NetworkException::class,
        CancellationException::class,
    )
    suspend fun logout(): LogoutResponse
}