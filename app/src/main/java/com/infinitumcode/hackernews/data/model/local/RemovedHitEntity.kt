package com.infinitumcode.hackernews.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Removed_Hit")
data class RemovedHitEntity(
    @PrimaryKey
    val hitObjectId: String
)