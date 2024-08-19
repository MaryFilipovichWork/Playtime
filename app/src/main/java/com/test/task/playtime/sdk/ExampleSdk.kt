package com.test.task.playtime.sdk

import androidx.lifecycle.LifecycleOwner

object ExampleSdk {

    private val playtimeManager: PlaytimeManager = PlaytimeManager(Playtime())

    fun attachActivity(lifecycleOwner: LifecycleOwner) {
        playtimeManager.initialize(lifecycleOwner)
    }

    fun getPlaytime() = playtimeManager.getPlaytime()
}