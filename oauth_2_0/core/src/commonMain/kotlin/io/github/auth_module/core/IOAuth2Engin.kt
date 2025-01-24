package io.github.auth_module.core

import io.github.auth_module.IAuthEngine
import io.github.auth_module.core.oauth2Client.IOAuth2Client
import io.github.auth_module.core.oauth2Client.data.TokensResponse

interface IOAuth2Engin<EnginConfig, LoginData>: IAuthEngine<EnginConfig, LoginData, TokensResponse, Any?, IOAuth2Client<LoginData>>, IProviderTokenManager