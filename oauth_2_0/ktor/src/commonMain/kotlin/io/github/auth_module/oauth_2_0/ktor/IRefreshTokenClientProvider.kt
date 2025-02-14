package io.github.auth_module.oauth_2_0.ktor

import io.github.auth_module.oauth_2_0.core.oauth2Client.IRefreshTokenClient
import io.ktor.client.*

interface IRefreshTokenClientProvider {
    fun getRefreshTokenClient(client: HttpClient): IRefreshTokenClient
}