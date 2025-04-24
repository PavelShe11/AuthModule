package io.github.auth_module.core.useCases

interface GetAuthStatusUseCase {
    suspend fun getStatus(): AuthStatus

    enum class AuthStatus {
        NotAuthorized,
        Authorized,
        AuthorizationIsConfirm
    }
}