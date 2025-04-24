package io.github.auth_module.oauth_2_0.core

import io.github.auth_module.oauth_2_0.core.tokensStore.FactoryTokenStore

open class OAuth2Config(
    var loginPath: String,
    var refreshTokenPath: String,
    var factoryTokenStore: FactoryTokenStore,
)