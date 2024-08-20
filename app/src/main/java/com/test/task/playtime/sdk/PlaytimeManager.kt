package com.test.task.playtime.sdk

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class PlaytimeManager(
    private val playtimeTracker: PlaytimeTracker
) {

    fun initialize(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                playtimeTracker.startTracking()
            }

            override fun onPause(owner: LifecycleOwner) {
                playtimeTracker.stopTracking()
            }
        })
    }

    fun getPlaytime() = playtimeTracker.playtime
}