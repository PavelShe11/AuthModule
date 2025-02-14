package io.github.auth_module.oauth_2_0.impl

import io.github.auth_module.oauth_2_0.core.tokensStore.FactoryTokenStore

class OAuth2EngineConfig(
    var loginPath: String,
    var refreshTokenPath: String,
    var factoryTokenStore: FactoryTokenStore,
)