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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

internal class OAuth2Manager<LoginData>(
    private val authClient: OAuth2Client<LoginData>,
    private val tokensStore: TokensStore,
    private val networkRequestCanceller: NetworkRequestCanceller
) : AuthManager<LoginData> {

    override suspend fun login(data: LoginData) = withContext(Dispatchers.Default) {
        try {
            val result = authClient.login(data)
            tokensStore.save(tokensResponseToTokensData(result))
        } catch (e: NetworkException) {
            throw LoginException(e.message, e)
        }
    }

    override suspend fun logout() = withContext(Dispatchers.Default) {
        networkRequestCanceller.cancel()
        tokensStore.reset()
    }

    override suspend fun getStatus(): GetAuthStatusUseCase.AuthStatus = withContext(Dispatchers.Default) {
        val tokens = tokensStore.get() ?: return@withContext NotAuthorized

        val expired = tokens.refreshTokenExpired
        val currentTime = Clock.System.now().epochSeconds
        if (currentTime > expired) {
            return@withContext NotAuthorized
        }

        try {
            authClient.refreshToken(RefreshTokenData(tokens.refreshToken))
        } catch (e: NetworkException) {
            return@withContext Authorized
        } catch (e: RefreshTokenException) {
            when (e) {
                is RefreshTokenException.Expired,
                is RefreshTokenException.IsMissing -> NotAuthorized
                is RefreshTokenException.NetworkError -> Authorized
            }
        }

        return@withContext AuthorizationIsConfirm
    }


}