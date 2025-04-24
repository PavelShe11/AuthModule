package io.github.pavelshe11.auth_module.sampleApp.umbrella.di

import io.github.pavelshe11.auth_module.featureAuth.presentation.AuthDIModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [AuthDIModule::class])
@ComponentScan("io.github.pavelshe11.auth_module.sampleApp.umbrella")
class UmbrellaDIModule