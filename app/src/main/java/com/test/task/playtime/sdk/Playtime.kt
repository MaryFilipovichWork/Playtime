package com.test.task.playtime.sdk

import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class Playtime(
    // TODO: get object to save and receive playtime from storage
) {

    private val _playtime = MutableStateFlow(0L)
    val playtime: StateFlow<Long>
        get() = _playtime

    private var startTrackingTime: Long = 0L
    private var job: Job? = null

    fun startTracking() {
        startTrackingTime = SystemClock.elapsedRealtime()
        Log.d(TAG, "tracking started")
        job = CoroutineScope(Dispatchers.Default + Job()).launch {
            flow {
                while (isActive) {
                    emit(SystemClock.elapsedRealtime() - startTrackingTime)
                    delay(1000.milliseconds)
                }
            }.collect { measuredTime ->
                _playtime.update {
                    measuredTime //TODO: then - savedTime + measuredTime
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