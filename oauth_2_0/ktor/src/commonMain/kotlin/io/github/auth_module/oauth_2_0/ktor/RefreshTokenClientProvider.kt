package io.github.auth_module.oauth_2_0.ktor

import io.github.auth_module.oauth_2_0.core.oauth2Client.RefreshTokenClient
import io.ktor.client.*

interface RefreshTokenClientProvider {
    fun getRefreshTokenClient(client: HttpClient): RefreshTokenClient
}