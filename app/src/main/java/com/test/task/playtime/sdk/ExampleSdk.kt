package com.test.task.playtime.sdk

import androidx.lifecycle.LifecycleOwner
import org.koin.java.KoinJavaComponent.inject

object ExampleSdk {

    private val playtimeManager: PlaytimeManager by inject(PlaytimeManager::class.java)

    fun attachActivity(lifecycleOwner: LifecycleOwner) {
        playtimeManager.initialize(lifecycleOwner)
    }

    fun getPlaytime() = playtimeManager.getPlaytime()
}