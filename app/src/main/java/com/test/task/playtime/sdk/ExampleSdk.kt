package com.test.task.playtime.sdk

import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow

object ExampleSdk {

    private val playtimeManager: PlaytimeManager = PlaytimeManager(Playtime())

    fun attachActivity(lifecycleOwner: LifecycleOwner) {
        playtimeManager.initialize(lifecycleOwner)
    }

    fun getPlaytime(): Flow<Long> = playtimeManager.getPlaytime()
}