package io.github.auth_module.oauth_2_0.ktor

import io.github.auth_module.core.NetworkRequestCanceller
import io.ktor.client.*
import kotlinx.coroutines.cancelChildren

class KtorNetworkRequestCanceller(
    private val client: HttpClient
): NetworkRequestCanceller {
    override fun cancel() {
        client.coroutineContext.cancelChildren()
    }
}