package com.infinitumcode.hackernews

import android.app.Application
import androidx.core.content.res.ResourcesCompat
import com.infinitumcode.hackernews.di.dataSourceModule
import com.infinitumcode.hackernews.di.domainModule
import com.infinitumcode.hackernews.di.localStorageModule
import com.infinitumcode.hackernews.di.mapperModule
import com.infinitumcode.hackernews.di.networkModule
import com.infinitumcode.hackernews.di.repositoryModule
import com.infinitumcode.hackernews.di.viewModelModule
import es.dmoral.toasty.Toasty
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
        setupToastyParams()
    }

    private fun setupToastyParams() {
        Toasty.Config.getInstance().allowQueue(false).setToastTypeface(
            ResourcesCompat.getFont(
                applicationContext,
                R.font.montserrat_regular
            )!!
        ).apply()
    }
}
