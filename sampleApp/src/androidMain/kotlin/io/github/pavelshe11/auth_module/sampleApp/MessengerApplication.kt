package io.github.pavelshe11.auth_module.sampleApp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MessengerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MessengerApplication)
            androidLogger(Level.DEBUG)
            modules()
        }
    }
}

