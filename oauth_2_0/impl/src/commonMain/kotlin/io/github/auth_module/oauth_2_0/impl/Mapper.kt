package io.github.auth_module.oauth_2_0.impl

import io.github.auth_module.oauth_2_0.core.oauth2Client.data.TokensResponse
import io.github.auth_module.oauth_2_0.core.tokensStore.TokensData

val tokensResponseToTokensData: (TokensResponse) -> TokensData
    get() = { tokensData ->
        TokensData(
            tokensData.accessToken,
            tokensData.accessTokenExpires,
            tokensData.refreshToken,
            tokensData.refreshTokenExpires
        )
    } 