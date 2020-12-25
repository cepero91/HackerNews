package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(localListHitUseCase = get(), removeHitUseCase = get(), mapHitToItem = get())
    }
}
