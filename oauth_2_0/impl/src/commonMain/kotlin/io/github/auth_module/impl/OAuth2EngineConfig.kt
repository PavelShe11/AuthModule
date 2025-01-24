package io.github.auth_module.impl

import io.github.auth_module.core.tokensStore.FactoryTokenStore

class OAuth2EngineConfig(
    var loginPath: String,
    var refreshTokenPath: String,
    var factoryTokenStore: FactoryTokenStore,
)