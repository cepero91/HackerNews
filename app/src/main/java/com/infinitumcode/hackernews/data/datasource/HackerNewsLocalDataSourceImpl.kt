package com.infinitumcode.hackernews.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.infinitumcode.hackernews.data.database.HackerNewsDatabase
import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.data.model.local.RemoteKeysEntity
import com.infinitumcode.hackernews.data.model.local.RemovedHitEntity
import com.infinitumcode.hackernews.utils.HIT_PER_PAGE
import kotlinx.coroutines.flow.Flow

class HackerNewsLocalDataSourceImpl(
    private val database: HackerNewsDatabase,
) : HackerNewsLocalDataSource {

    override fun getLocalHits(query: String?): PagingSource<Int, HitEntity> {
        return database.hackerNewsDao().allHits()
    }

    override suspend fun getRemotedHits(): List<RemovedHitEntity> {
        return database.removedHitDao().removedHits()
    }

    override suspend fun remoteKeyById(hitObjectId: String): RemoteKeysEntity? {
        return database.remoteKeysDao().remoteKeysRepoId(hitObjectId)
    }

    override suspend fun clearLocalHits() {
        database.hackerNewsDao().clearAllHits()
    }

    override suspend fun clearRemoteKeys() {
        database.remoteKeysDao().clearRemoteKeys()
    }

    override suspend fun insertRemoteKeys(remoteKeys: List<RemoteKeysEntity>) {
        database.remoteKeysDao().insertAll(remoteKeys)
    }

    override suspend fun doOperationInTransaction(method: suspend () -> Unit) {
        database.withTransaction {
            method()
        }
    }

    override suspend fun insertHits(hits: MutableList<HitEntity>) {
        val removedHits = database.removedHitDao().removedHits()
        if (removedHits.isNotEmpty()) {
            hits.filter {
                removedHits.contains(RemovedHitEntity(it.objectId)).not()
            }
        }
        database.hackerNewsDao().insertAll(hits)
    }


    override suspend fun removeItemById(hitObjectId: String): Boolean {
        database.removedHitDao().insert(RemovedHitEntity(hitObjectId))
        return database.hackerNewsDao().deleteHit(hitObjectId) == 1

    }
}