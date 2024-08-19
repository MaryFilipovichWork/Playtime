package com.test.task.playtime.sdk

import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

class Playtime(
    // TODO: get object to save and receive playtime from storage
) {

    private val _playtime = MutableStateFlow<Long>(0L)
    val playtime: StateFlow<Long>
        get() = _playtime

    private var startTrackingTime: Long = 0L
    private var job: Job? = null

    fun startTracking() {
        startTrackingTime = SystemClock.elapsedRealtime()
        Log.d(TAG, "tracking started")
        job = CoroutineScope(Dispatchers.Default + Job()).launch {
            while (isActive) {
                delay(1000L)
                val currentTime = SystemClock.elapsedRealtime()
                val measuredTime = currentTime - startTrackingTime
                    _playtime.update {
                        measuredTime / 1000 //TODO: then - savedTime + measuredTime
                    }
                    Log.d(TAG, "playtime = ${playtime.value}")
            }
        }
    }

    fun stopTracking() {
        job?.cancel()
        Log.d(TAG, "tracking stopped")
        val currentTime = SystemClock.elapsedRealtime()
        val measuredTime = (currentTime - startTrackingTime) / 1000
        _playtime.update {
            _playtime.value + measuredTime //TODO: savedTime + measuredTime
        }
        //TODO: save time
    }

    companion object {
        private const val TAG = "Playtime"
    }
}