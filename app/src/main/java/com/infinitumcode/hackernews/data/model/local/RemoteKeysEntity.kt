package com.infinitumcode.hackernews.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Remote_Keys")
data class RemoteKeysEntity(
    @PrimaryKey
    val repoId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)