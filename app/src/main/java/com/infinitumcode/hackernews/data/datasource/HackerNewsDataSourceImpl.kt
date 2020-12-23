package com.infinitumcode.hackernews.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.infinitumcode.hackernews.data.api.HackerNewsService
import com.infinitumcode.hackernews.data.database.HackerNewsDatabase
import com.infinitumcode.hackernews.data.mapper.MapHitDtoToEntity
import com.infinitumcode.hackernews.data.model.local.HitEntity
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class HackerNewsDataSourceImpl(
    private val service: HackerNewsService,
    private val database: HackerNewsDatabase,
    private val mapHitDtoToEntity: MapHitDtoToEntity
) : HackerNewsDataSource {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    override fun searchByQuery(query: String?): Flow<PagingData<HitEntity>> {
        val pagingSourceFactory = { database.hackerNewsDao().allHits() }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = HackerNewsRemoteMediator(query, service, database, mapHitDtoToEntity),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}