package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.data.mapper.MapHitDtoToEntity
import com.infinitumcode.hackernews.data.mapper.MapHitEntityToDomain
import com.infinitumcode.hackernews.ui.main.mapper.MapHitToItem
import org.koin.dsl.module

val mapperModule = module {
    single { MapHitEntityToDomain() }
    single { MapHitDtoToEntity() }
    single { MapHitToItem() }
}
