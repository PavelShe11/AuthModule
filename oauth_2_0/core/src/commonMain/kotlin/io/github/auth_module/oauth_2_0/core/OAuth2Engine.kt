package io.github.auth_module.oauth_2_0.core

import io.github.auth_module.core.AuthEngine
import io.github.auth_module.oauth_2_0.core.TokensManager.ProviderTokenManager
import io.github.auth_module.oauth_2_0.core.oauth2Client.OAuth2Client
import io.github.auth_module.oauth_2_0.core.oauth2Client.data.TokensResponse

interface OAuth2Engine<EnginConfig, LoginData>: AuthEngine<EnginConfig, LoginData, TokensResponse, Any?, OAuth2Client<LoginData>>,
    ProviderTokenManager