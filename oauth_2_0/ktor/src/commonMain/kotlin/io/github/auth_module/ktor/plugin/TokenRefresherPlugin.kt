package io.github.auth_module.ktor.plugin

import io.github.auth_module.core.Auth
import io.github.auth_module.ktor.getTokensManager
import io.ktor.client.plugins.api.*
import io.ktor.http.*

val TokenRefresherPlugin = createClientPlugin("OAuth2.0 Token Refresher") {
    val tokensManager = Auth.getTokensManager(client)

    fun ignorePaths(): List<String> {
        val config = Auth.config
        return listOf(config.loginPath, config.refreshTokenPath)
    }

    onRequest { request, _ ->
        val ignorePaths = ignorePaths()
        if (ignorePaths.contains(request.url.fragment)) return@onRequest
        val accessToken = tokensManager.getActualAccessTokenOrNull()
        if (accessToken == null || accessToken == "") return@onRequest
        request.headers.append("Authorization", "Bearer $accessToken")
    }

    on(Send) { request ->
        val originalCall = proceed(request)
        originalCall.response.run {
            val ignorePaths = ignorePaths()
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