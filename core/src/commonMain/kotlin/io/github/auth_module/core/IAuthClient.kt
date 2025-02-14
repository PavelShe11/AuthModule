package io.github.auth_module.core

import io.github.auth_module.core.exception.LoginException
import io.github.auth_module.core.exception.LogoutException
import io.github.auth_module.core.exception.SomethingWentWrongException
import kotlin.coroutines.cancellation.CancellationException

interface IAuthClient<in LoginData, out LoginResponse, out LogoutResponse> {

    @Throws(SomethingWentWrongException::class, LoginException::class, CancellationException::class)
    suspend fun login(data: LoginData): LoginResponse

    @Throws(SomethingWentWrongException::class, LogoutException::class, CancellationException::class)
    suspend fun logout(): LogoutResponse
}