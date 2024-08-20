package com.test.task.playtime

import android.app.Application
import com.test.task.playtime.di.sdkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PlaytimeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PlaytimeApp)
            modules(sdkModule)
        }
    }
}