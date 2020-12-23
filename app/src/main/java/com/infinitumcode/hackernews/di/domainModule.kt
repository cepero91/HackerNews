package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.domain.usecase.SearchByDateUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        SearchByDateUseCase(repository = get())
    }
}