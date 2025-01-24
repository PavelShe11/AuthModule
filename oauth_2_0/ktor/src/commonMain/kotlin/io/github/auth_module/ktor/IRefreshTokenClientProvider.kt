package io.github.auth_module.ktor

import io.github.auth_module.core.oauth2Client.IRefreshTokenClient
import io.ktor.client.*

interface IRefreshTokenClientProvider {
    fun getRefreshTokenClient(client: HttpClient): IRefreshTokenClient
}