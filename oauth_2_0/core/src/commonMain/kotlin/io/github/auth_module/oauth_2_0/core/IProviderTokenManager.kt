package io.github.auth_module.oauth_2_0.core

import io.github.auth_module.oauth_2_0.core.oauth2Client.IRefreshTokenClient

interface IProviderTokenManager {
    fun getTokensManager(authClient: IRefreshTokenClient): ITokensManager
}