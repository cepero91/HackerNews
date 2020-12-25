package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.data.database.HackerNewsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localStorageModule = module {
    single { HackerNewsDatabase.getInstance(androidContext()) }
}
