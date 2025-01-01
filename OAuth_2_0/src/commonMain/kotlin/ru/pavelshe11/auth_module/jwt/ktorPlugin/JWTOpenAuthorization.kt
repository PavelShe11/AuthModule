package ru.pavelshe11.auth_module.jwt.ktorPlugin

import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.http.HttpStatusCode
import ru.pavelshe11.auth_module.jwt.Auth

val JWTOpenAuthorization = createClientPlugin("InstallingAuthorization") {
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