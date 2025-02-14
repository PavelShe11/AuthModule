package io.github.auth_module.oauth_2_0.ktor.plugin

import io.ktor.client.plugins.api.*
import io.ktor.http.*

val TokenRefresherPlugin = createClientPlugin("OAuth2.0 Token Refresher", ::TokenRefresherPluginConfig) {
    val refreshTokenClientProvider = pluginConfig.refreshTokenClientProvider ?: return@createClientPlugin
    val tokensManager = pluginConfig.providerTokenManager?.getTokensManager(refreshTokenClientProvider.getRefreshTokenClient(client)) ?: return@createClientPlugin

    onRequest { request, _ ->
        val ignorePaths = tokensManager.ignoredPathsForTokenRefresh()
        if (ignorePaths.contains(request.url.fragment)) return@onRequest
        val accessToken = tokensManager.getActualAccessTokenOrNull()
        if (accessToken == null || accessToken == "") return@onRequest
        request.headers.append("Authorization", "Bearer $accessToken")
    }

    on(Send) { request ->
        val originalCall = proceed(request)
        originalCall.response.run {
            val ignorePaths = tokensManager.ignoredPathsForTokenRefresh()
            if (ignorePaths.contains(request.url.fragment)) return@run originalCall

            if (status != HttpStatusCode.Unauthorized) return@run originalCall

            tokensManager.refreshToken()
            val accessToken = tokensManager.getActualAccessTokenOrNull()
            if (accessToken == null || accessToken == "") return@run originalCall

            request.headers.append("Authorization", "Bearer $accessToken")
            proceed(request)
        }
    }
}

