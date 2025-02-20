package io.github.pavelshe11.auth_module.sampleApp.feature.auth.data

import io.github.auth_module.oauth_2_0.core.oauth2Client.OAuth2Client
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.LoginData
import io.github.pavelshe11.auth_module.sampleApp.feature.auth.domain.SendCodeConfirmUseCase

interface AuthClient: OAuth2Client<LoginData>, SendCodeConfirmUseCase