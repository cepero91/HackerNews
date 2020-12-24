package com.infinitumcode.hackernews.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.data.model.local.RemoteKeysEntity
import com.infinitumcode.hackernews.data.model.local.RemovedHitEntity

@Dao
interface RemovedHitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(removedHit: RemovedHitEntity)

    @Query("SELECT * FROM Removed_Hit")
    suspend fun removedHits(): List<RemovedHitEntity>

}