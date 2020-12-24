package com.infinitumcode.hackernews.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.infinitumcode.hackernews.data.model.local.HitEntity

@Dao
interface HackerNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<HitEntity>)

    @Query("SELECT * FROM Hit ORDER BY createdAt DESC")
    fun allHits(): PagingSource<Int, HitEntity>

    @Query("DELETE FROM Hit")
    suspend fun clearAllHits()

    @Query("DELETE FROM Hit WHERE objectId = :hitObjectId")
    suspend fun deleteHit(hitObjectId: String): Int

    @Query("SELECT COUNT(*) FROM Hit")
    fun getHitCount(): Int
}