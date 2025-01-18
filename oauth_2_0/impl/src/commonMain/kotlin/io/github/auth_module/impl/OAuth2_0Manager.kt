package io.github.auth_module.impl

import io.github.auth_module.AuthManager
import io.github.auth_module.core.NetworkRequestCanceller
import io.github.auth_module.core.authClient.IAuthClient
import io.github.auth_module.core.tokensStore.TokensData
import io.github.auth_module.exception.SomethingWentWrongException
import io.github.auth_module.impl.tokensStore.TokensStore

internal class OAuth2_0Manager(
    private val authClient: IAuthClient,
    private val tokensStore: TokensStore,
    private val networkRequestCanceller: NetworkRequestCanceller
): AuthManager {

    override suspend fun <LoginData> login(data: LoginData) {
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