package com.test.task.playtime.sdk

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LifecycleOwner

object ExampleSdk {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "playtime_prefs")

    private lateinit var playtimeManager: PlaytimeManager

    fun attachActivity(lifecycleOwner: LifecycleOwner, applicationContext: Context) {
        playtimeManager = PlaytimeManager(PlaytimeTracker(PlayTimeDataSource(applicationContext.dataStore)))
        playtimeManager.initialize(lifecycleOwner)
    }

    fun getPlaytime() = playtimeManager.getPlaytime()
}