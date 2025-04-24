package io.github.pavelshe11.auth_module.sampleApp.androidApp

import android.app.Application
import io.github.pavelshe11.auth_module.sampleApp.umbrella.di.initKoin

class MessengerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}

