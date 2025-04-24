package io.github.auth_module.core

class AuthConfigBuilder <EngineConfig> {

    internal var engineConfig: EngineConfig.() -> Unit = {}

    fun engine(block: EngineConfig.() -> Unit) {
        val oldConfig = engineConfig
        engineConfig = {
            oldConfig()
            block()
        }
    }

}