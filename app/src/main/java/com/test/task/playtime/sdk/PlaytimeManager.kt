package com.test.task.playtime.sdk

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class PlaytimeManager(
    private val playtime: Playtime
) {

    fun initialize(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                playtime.startTracking()
            }

            override fun onPause(owner: LifecycleOwner) {
                playtime.stopTracking()
            }
        })
    }

    fun getPlaytime() = playtime.playtime
}