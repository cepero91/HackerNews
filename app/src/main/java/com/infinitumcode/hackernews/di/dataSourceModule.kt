package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.data.datasource.HackerNewsLocalDataSource
import com.infinitumcode.hackernews.data.datasource.HackerNewsLocalDataSourceImpl
import com.infinitumcode.hackernews.data.datasource.HackerNewsRemoteDataSource
import com.infinitumcode.hackernews.data.datasource.HackerNewsRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<HackerNewsLocalDataSource> {
        HackerNewsLocalDataSourceImpl(
            database = get()
        )
    }

    factory<HackerNewsRemoteDataSource> {
        HackerNewsRemoteDataSourceImpl(
            service = get()
        )
    }
}
