package com.infinitumcode.hackernews.domain.repository

import androidx.paging.PagingData
import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.domain.model.Hit
import kotlinx.coroutines.flow.Flow

interface HackerNewsRepository {
    fun searchByQuery(query: String?): Flow<PagingData<Hit>>
}