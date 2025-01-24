package io.github.auth_module.core

import io.github.auth_module.core.oauth2Client.IRefreshTokenClient

interface IProviderTokenManager {
    fun getTokensManager(authClient: IRefreshTokenClient): ITokensManager
}