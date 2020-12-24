package com.infinitumcode.hackernews.data.model.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Hit", indices = [Index(value = ["storyTitle"], unique = true)])
data class HitEntity(
    @PrimaryKey
    val objectId: String,
    val storyTitle: String?,
    val author: String,
    val createdAt: DateTime,
    val storyUrl: String?
)
