package io.github.auth_module.oauth_2_0.impl

import io.github.auth_module.core.AuthManager
import io.github.auth_module.core.NetworkRequestCanceller
import io.github.auth_module.core.exception.SomethingWentWrongException
import io.github.auth_module.oauth_2_0.core.oauth2Client.IOAuth2Client
import io.github.auth_module.oauth_2_0.core.tokensStore.TokensData
import io.github.auth_module.oauth_2_0.impl.tokensStore.TokensStore

internal class OAuth2Manager<LoginData>(
    private val authClient: IOAuth2Client<LoginData>,
    private val tokensStore: TokensStore,
    private val networkRequestCanceller: NetworkRequestCanceller
): AuthManager<LoginData> {

    override suspend fun login(data: LoginData) {
        val result = authClient.login(data)

        if (result.accessToken.isNullOrEmpty() || result.refreshToken.isNullOrEmpty()) {
            throw SomethingWentWrongException("Tokens are missing")
        }

        if (result.accessTokenExpires == null) {
            throw SomethingWentWrongException("The access token is missing the expired field")
        }

        if (result.refreshTokenExpires == null) {
            throw SomethingWentWrongException("The refresh token is missing the expired field")
        }

        val tokensData = TokensData(
            result.accessToken!!,
            result.accessTokenExpires!!,
            result.refreshToken!!,
            result.refreshTokenExpires!!
        )

        tokensStore.save(tokensData)
    }

    override suspend fun logout() {
        networkRequestCanceller.cancel()
        tokensStore.reset()
    }

}