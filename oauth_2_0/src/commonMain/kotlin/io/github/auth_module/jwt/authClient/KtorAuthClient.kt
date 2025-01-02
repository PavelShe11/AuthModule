package io.github.auth_module.jwt.authClient

import io.github.auth_module.KSerializable
import io.github.auth_module.jwt.authClient.data.RefreshTokenData
import io.github.auth_module.jwt.authClient.data.TokensResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

internal class KtorAuthClient(
    private val client: HttpClient,
    private val config: AuthClientConfig
) {

    suspend fun <LoginData: KSerializable<LoginData>>login(data: LoginData): TokensResponse {
        return client.post(config.loginPath) {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(data.serializer, data))
        }.body<TokensResponse>()
    }

    suspend fun refreshToken(data: RefreshTokenData): TokensResponse {
        return client.post(config.refreshTokenPath) {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(RefreshTokenData.serializer(), data))
        }.body<TokensResponse>()
    }

}