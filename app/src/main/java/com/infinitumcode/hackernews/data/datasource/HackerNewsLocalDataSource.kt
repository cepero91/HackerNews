package com.infinitumcode.hackernews.data.datasource

import androidx.paging.PagingSource
import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.data.model.local.RemoteKeysEntity
import com.infinitumcode.hackernews.data.model.local.RemovedHitEntity

interface HackerNewsLocalDataSource {
    fun getLocalHits(query: String?): PagingSource<Int, HitEntity>
    suspend fun getRemotedHits(): List<RemovedHitEntity>
    suspend fun remoteKeyById(hitObjectId: String): RemoteKeysEntity?
    suspend fun clearLocalHits()
    suspend fun clearRemoteKeys()
    suspend fun insertRemoteKeys(remoteKeys: List<RemoteKeysEntity>)
    suspend fun doOperationInTransaction(method: suspend () -> Unit)
    suspend fun insertHits(hits: MutableList<HitEntity>)
    suspend fun removeItemById(hitObjectId: String): Boolean
}
