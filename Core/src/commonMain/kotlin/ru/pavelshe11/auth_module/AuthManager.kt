package ru.pavelshe11.auth_module

import ru.pavelshe11.auth_module.exception.LoginException
import ru.pavelshe11.auth_module.exception.LogoutException
import ru.pavelshe11.auth_module.exception.SomethingWentWrongException
import kotlin.coroutines.cancellation.CancellationException

interface AuthManager {
    @Throws(SomethingWentWrongException::class, LoginException::class, CancellationException::class)
    suspend fun <LoginData: KSerializable<LoginData>>login(data: LoginData)

    @Throws(SomethingWentWrongException::class, LogoutException::class, CancellationException::class)
    suspend fun logout()
}