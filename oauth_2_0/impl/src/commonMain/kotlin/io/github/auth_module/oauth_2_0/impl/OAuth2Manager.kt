package io.github.auth_module.oauth_2_0.impl

import io.github.auth_module.core.AuthManager
import io.github.auth_module.core.NetworkRequestCanceller
import io.github.auth_module.core.exception.LoginException
import io.github.auth_module.core.exception.NetworkException
import io.github.auth_module.core.useCases.GetAuthStatusUseCase
import io.github.auth_module.core.useCases.GetAuthStatusUseCase.AuthStatus.*
import io.github.auth_module.oauth_2_0.core.exception.RefreshTokenException
import io.github.auth_module.oauth_2_0.core.oauth2Client.OAuth2Client
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.RefreshTokenData
import io.github.auth_module.oauth_2_0.impl.tokensStore.TokensStore
import kotlinx.datetime.Clock

internal class OAuth2Manager<LoginData>(
    private val authClient: OAuth2Client<LoginData>,
    private val tokensStore: TokensStore,
    private val networkRequestCanceller: NetworkRequestCanceller
) : AuthManager<LoginData> {

    override suspend fun login(data: LoginData) {
        try {
            val result = authClient.login(data)
            tokensStore.save(tokensResponseToTokensData(result))
        } catch (e: NetworkException) {
            throw LoginException(e.message, e)
        }
    }

    override suspend fun logout() {
        networkRequestCanceller.cancel()
        tokensStore.reset()
    }

    override suspend fun getStatus(): GetAuthStatusUseCase.AuthStatus {
        val tokens = tokensStore.get() ?: return NotAuthorized

        val expired = tokens.refreshTokenExpired
        val currentTime = Clock.System.now().epochSeconds
        if (currentTime > expired) {
            return NotAuthorized
        }

        try {
            authClient.refreshToken(RefreshTokenData(tokens.refreshToken))
        } catch (e: NetworkException) {
            return Authorized
        } catch (e: RefreshTokenException) {
            when (e) {
                is RefreshTokenException.Expired,
                is RefreshTokenException.IsMissing -> NotAuthorized
                is RefreshTokenException.NetworkError -> Authorized
            }
        }

        return AuthorizationIsConfirm
    }


}