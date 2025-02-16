package io.github.auth_module.oauth_2_0.ktor.plugin

import io.github.auth_module.oauth_2_0.core.exception.AccessTokenException
import io.ktor.client.plugins.api.*
import io.ktor.http.*

val TokenRefresherPlugin = createClientPlugin("OAuth2.0 Token Refresher", ::TokenRefresherPluginConfig) {
    val refreshTokenClientProvider = pluginConfig.refreshTokenClientProvider ?: return@createClientPlugin
    val tokensManager =
        pluginConfig.providerTokenManager?.getTokensManager(refreshTokenClientProvider.getRefreshTokenClient(client))
            ?: return@createClientPlugin

    onRequest { request, _ ->
        val ignorePaths = tokensManager.ignoredPathsForTokenRefresh()
        if (ignorePaths.contains(request.url.fragment)) return@onRequest
        try {
            val accessToken = tokensManager.getActualAccessToken()
            request.headers.append("Authorization", "Bearer $accessToken")
        } catch (ex: AccessTokenException) {
            return@onRequest
        }
    }

    on(Send) { request ->
        val originalCall = proceed(request)
        originalCall.response.run {
            val ignorePaths = tokensManager.ignoredPathsForTokenRefresh()
            if (ignorePaths.contains(request.url.fragment)) return@on originalCall

            if (status != HttpStatusCode.Unauthorized) return@on originalCall

            try {
                val accessToken = tokensManager.getActualAccessToken()
                request.headers.append("Authorization", "Bearer $accessToken")
                proceed(request)
            } catch (ex: AccessTokenException) {
                return@on originalCall
            }
        }
    }
}

