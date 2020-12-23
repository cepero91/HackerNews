package com.infinitumcode.hackernews.data.database

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.infinitumcode.hackernews.data.model.local.HitEntity

interface HackerNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<HitEntity>)

    @Query("SELECT * FROM Hit ORDER BY createdAt DESC")
    fun allHits(queryString: String): PagingSource<Int, HitEntity>

    @Query("DELETE FROM Hit")
    suspend fun clearAllHits()

    @Query("DELETE FROM Hit WHERE objectID = :hitObjectID")
    suspend fun deleteHit(hitObjectID: String)
}