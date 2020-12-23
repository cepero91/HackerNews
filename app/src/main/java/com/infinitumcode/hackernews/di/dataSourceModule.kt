package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.data.datasource.HackerNewsDataSource
import com.infinitumcode.hackernews.data.datasource.HackerNewsDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<HackerNewsDataSource> {
        HackerNewsDataSourceImpl(
            service = get(),
            database = get(),
            mapHitDtoToEntity = get()
        )
    }
}
