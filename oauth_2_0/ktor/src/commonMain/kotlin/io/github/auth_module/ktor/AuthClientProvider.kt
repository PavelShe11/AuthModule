package io.github.auth_module.ktor

import io.github.auth_module.core.authClient.IAuthClient
import io.ktor.client.*

interface AuthClientProvider {
    fun getAuthClient(client: HttpClient): IAuthClient
}