package ru.shelldev.auth_module.jwt.authClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import ru.shelldev.auth_module.KSerializable
import ru.shelldev.auth_module.jwt.authClient.data.RefreshTokenData
import ru.shelldev.auth_module.jwt.authClient.data.TokensResponse

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