package io.github.auth_module.core

import io.github.auth_module.Auth
import io.github.auth_module.core.oauth2Client.IOAuth2Client
import io.github.auth_module.core.oauth2Client.data.TokensResponse

typealias OAuth2 = Auth<*, *, TokensResponse, Any?, IOAuth2Client<*>>