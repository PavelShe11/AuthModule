package io.github.auth_module.oauth_2_0.core

import io.github.auth_module.core.IAuthEngine
import io.github.auth_module.oauth_2_0.core.oauth2Client.IOAuth2Client
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.TokensResponse

interface IOAuth2Engin<EnginConfig, LoginData>: IAuthEngine<EnginConfig, LoginData, TokensResponse, Any?, IOAuth2Client<LoginData>>,
    IProviderTokenManager