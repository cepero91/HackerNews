package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.data.repository.HackerNewsRepositoryImpl
import com.infinitumcode.hackernews.domain.repository.HackerNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<HackerNewsRepository> {
        HackerNewsRepositoryImpl(
            hackerNewsLocalDataSource = get(),
            hackerNewsRemoteDataSource = get(),
            mapHitEntityToDomain = get()
        )
    }
}
