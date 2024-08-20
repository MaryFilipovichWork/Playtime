package com.test.task.playtime.sdk

import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class PlaytimeTracker(
    private val dataSource: PlayTimeDataSource
) {

    private val _playtime = MutableStateFlow(0L)
    val playtime: StateFlow<Long>
        get() = _playtime

    private var job: Job? = null

    fun startTracking() {
        val startTrackingTime = SystemClock.elapsedRealtime()
        Log.d(TAG, "tracking started")
        job = CoroutineScope(Dispatchers.Default + Job()).launch {
            val previousSessionsTime = dataSource.getPlaytime().first()
            _playtime.update { previousSessionsTime }
            flow {
                while (isActive) {
                    emit((SystemClock.elapsedRealtime() - startTrackingTime) / 1000)
                    delay(1000.milliseconds)
                }
            }.collect { measuredTime ->
                _playtime.update {
                    previousSessionsTime + measuredTime
                }
                Log.d(TAG, "playtime = ${playtime.value}")
            }
        }
    }

    fun stopTracking() {
        job?.cancel()
        Log.d(TAG, "tracking stopped")
        CoroutineScope(Dispatchers.IO).launch {
            dataSource.savePlaytime(_playtime.value)
        }
    }

    companion object {
        private const val TAG = "PlaytimeTracker"
    }
}