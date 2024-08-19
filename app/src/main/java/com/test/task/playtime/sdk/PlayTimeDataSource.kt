package com.test.task.playtime.sdk

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PlayTimeDataSource(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun savePlaytime(playtime: Long) {
        dataStore.edit { preferences ->
            preferences[PLAYTIME_KEY] = playtime
        }
    }

    fun getPlaytime(): Flow<Long> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[PLAYTIME_KEY] ?: 0L
            }

    companion object {
        private val PLAYTIME_KEY = longPreferencesKey("playtime_key")
    }
}