package com.infinitumcode.hackernews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.infinitumcode.hackernews.data.datasource.HackerNewsLocalDataSource
import com.infinitumcode.hackernews.data.datasource.HackerNewsRemoteDataSource
import com.infinitumcode.hackernews.data.mapper.MapHitEntityToDomain
import com.infinitumcode.hackernews.data.paging.HackerNewRemoteMediator
import com.infinitumcode.hackernews.domain.model.Hit
import com.infinitumcode.hackernews.domain.repository.HackerNewsRepository
import com.infinitumcode.hackernews.utils.HIT_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HackerNewsRepositoryImpl(
    private val hackerNewsLocalDataSource: HackerNewsLocalDataSource,
    private val hackerNewsRemoteDataSource: HackerNewsRemoteDataSource,
    private val mapHitEntityToDomain: MapHitEntityToDomain
) :
    HackerNewsRepository {

    override fun allHits(query: String?): Flow<PagingData<Hit>> {
        val pagingSourceFactory = { hackerNewsLocalDataSource.getLocalHits(query) }

        return Pager(
            config = PagingConfig(
                pageSize = HIT_PER_PAGE,
                enablePlaceholders = false
            ),
            remoteMediator = HackerNewRemoteMediator(
                remoteDataSource = hackerNewsRemoteDataSource,
                localDataSource = hackerNewsLocalDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map {
                mapHitEntityToDomain.map(it)
            }
        }
    }

    override suspend fun removeItemById(hitObjectId: String): Boolean {
        return hackerNewsLocalDataSource.removeItemById(hitObjectId)
    }
}