package com.infinitumcode.hackernews

import android.app.Application
import com.infinitumcode.hackernews.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class HackerNewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@HackerNewsApp)
            modules(
                dataSourceModule,
                domainModule,
                localStorageModule,
                mapperModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

}