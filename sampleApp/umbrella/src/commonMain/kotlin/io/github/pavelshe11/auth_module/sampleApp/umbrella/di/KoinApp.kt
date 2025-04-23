package io.github.pavelshe11.auth_module.sampleApp.umbrella.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.ksp.generated.module

fun initKoin(config: KoinAppDeclaration = {}) {
    startKoin {
        printLogger()
        includes(config)
        modules(UmbrellaDIModule().module)
    }
}