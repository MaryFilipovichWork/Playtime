package com.test.task.playtime.di

import com.test.task.playtime.sdk.PlayTimeDataSource
import com.test.task.playtime.sdk.PlaytimeManager
import com.test.task.playtime.sdk.PlaytimeTracker
import com.test.task.playtime.utils.dataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sdkModule = module {
    single { PlayTimeDataSource(androidApplication().dataStore) }
    single { PlaytimeTracker(get()) }
    single { PlaytimeManager(get()) }
}