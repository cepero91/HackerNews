package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.domain.usecase.LocalListHitUseCase
import com.infinitumcode.hackernews.domain.usecase.RemoveHitUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        LocalListHitUseCase(repository = get())
    }

    factory {
        RemoveHitUseCase(repository = get())
    }
}
