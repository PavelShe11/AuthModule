package io.github.auth_module.core

import io.github.auth_module.core.useCases.GetAuthStatusUseCase
import io.github.auth_module.core.useCases.LoginUseCase
import io.github.auth_module.core.useCases.LogoutUseCase

interface AuthManager<LoginData>: LoginUseCase<LoginData>, LogoutUseCase, GetAuthStatusUseCase