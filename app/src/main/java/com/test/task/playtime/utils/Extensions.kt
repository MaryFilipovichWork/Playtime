package com.test.task.playtime.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import java.util.Locale

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "playtime_prefs")

fun Long.secondsToTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val secs = this % 60

    return String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, secs)
}