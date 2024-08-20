package com.test.task.playtime.utils

import java.util.Locale

fun Long.secondsToTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val secs = this % 60

    return String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, secs)
}