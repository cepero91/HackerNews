package com.infinitumcode.hackernews.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.infinitumcode.hackernews.data.api.HackerNewsService
import com.infinitumcode.hackernews.data.database.HackerNewsDatabase
import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.data.model.local.RemoteKeysEntity
import com.infinitumcode.hackernews.data.model.remote.Hit
import com.infinitumcode.hackernews.utils.DEFAULT_QUERY
import com.infinitumcode.hackernews.utils.DateUtil
import com.infinitumcode.hackernews.utils.STARTING_INDEX_PAGE
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class HackerNewsRemoteMediator(
    private val query: String?,
    private val service: HackerNewsService,
    private val dataBase: HackerNewsDatabase
) : RemoteMediator<Int, Hit>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hit>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_INDEX_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                    ?: // The LoadType is PREPEND so some data was loaded before,
                    // so we should have been able to get remote keys
                    // If the remoteKeys are null, then we're an invalid state and we have a bug
                    throw InvalidObjectException("Remote key and the prevKey should not be null")
                // If the previous key is null, then we can't request more data
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextKey
            }
        }

        val apiQuery = query ?: DEFAULT_QUERY

        try {
            val apiResponse = service.searchByDate(page, apiQuery)

            val hits = apiResponse.hits
            val endOfPaginationReached = hits.isEmpty()
            dataBase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    dataBase.remoteKeysDao().clearRemoteKeys()
                    dataBase.hackerNewsDao().clearAllHits()
                }
                val prevKey = if (page == STARTING_INDEX_PAGE) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = hits.map {
                    RemoteKeysEntity(repoId = it.storyId, prevKey = prevKey, nextKey = nextKey)
                }
                val hitEntities = hits.map {
                    HitEntity(
                        storyId = it.storyId,
                        storyTitle = it.storyTitle,
                        storyUrl = it.storyUrl,
                        author = it.author,
                        createdAt = DateUtil.fromStringToDateTime(it.createdAt)
                    )
                }
                dataBase.remoteKeysDao().insertAll(keys)
                dataBase.hackerNewsDao().insertAll(hitEntities)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hit>): RemoteKeysEntity? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hit ->
                // Get the remote keys of the last item retrieved
                dataBase.remoteKeysDao().remoteKeysRepoId(hit.storyId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hit>): RemoteKeysEntity? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hit ->
                // Get the remote keys of the first items retrieved
                dataBase.remoteKeysDao().remoteKeysRepoId(hit.storyId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Hit>
    ): RemoteKeysEntity? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.storyId?.let { id ->
                dataBase.remoteKeysDao().remoteKeysRepoId(id)
            }
        }
    }
}