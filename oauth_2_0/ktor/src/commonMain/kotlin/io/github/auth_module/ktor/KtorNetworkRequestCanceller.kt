package io.github.auth_module.ktor

import io.github.auth_module.NetworkRequestCanceller
import io.ktor.client.*
import kotlinx.coroutines.cancelChildren

class KtorNetworkRequestCanceller(
    private val client: HttpClient
): NetworkRequestCanceller {
    override fun cancel() {
        client.coroutineContext.cancelChildren()
    }
}