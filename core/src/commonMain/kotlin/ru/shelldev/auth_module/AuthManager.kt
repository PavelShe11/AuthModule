package ru.shelldev.auth_module

import ru.shelldev.auth_module.exception.LoginException
import ru.shelldev.auth_module.exception.LogoutException
import ru.shelldev.auth_module.exception.SomethingWentWrongException
import kotlin.coroutines.cancellation.CancellationException

interface AuthManager {
    @Throws(SomethingWentWrongException::class, LoginException::class, CancellationException::class)
    suspend fun <LoginData: KSerializable<LoginData>>login(data: LoginData)

    @Throws(SomethingWentWrongException::class, LogoutException::class, CancellationException::class)
    suspend fun logout()
}