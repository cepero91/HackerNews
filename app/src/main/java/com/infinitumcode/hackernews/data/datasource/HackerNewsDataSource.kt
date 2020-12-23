package com.infinitumcode.hackernews.data.datasource

import androidx.paging.PagingData
import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.domain.model.Hit
import kotlinx.coroutines.flow.Flow

interface HackerNewsDataSource {
    fun searchByQuery(query: String?): Flow<PagingData<HitEntity>>
}