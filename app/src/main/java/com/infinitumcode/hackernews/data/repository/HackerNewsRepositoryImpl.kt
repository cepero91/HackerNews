package com.infinitumcode.hackernews.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.infinitumcode.hackernews.data.datasource.HackerNewsDataSource
import com.infinitumcode.hackernews.data.mapper.MapHitEntityToDomain
import com.infinitumcode.hackernews.domain.model.Hit
import com.infinitumcode.hackernews.domain.repository.HackerNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HackerNewsRepositoryImpl(
    private val hackerNewsDataSource: HackerNewsDataSource,
    private val mapHitEntityToDomain: MapHitEntityToDomain
) :
    HackerNewsRepository {

    override fun searchByQuery(query: String?): Flow<PagingData<Hit>> {
        return hackerNewsDataSource.searchByQuery(query).map { pagingData ->
            pagingData.map { hitEntity -> mapHitEntityToDomain.map(hitEntity) }
        }
    }
}