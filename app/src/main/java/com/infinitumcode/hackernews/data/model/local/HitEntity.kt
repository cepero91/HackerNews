package com.infinitumcode.hackernews.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "Hit")
data class HitEntity(
    @PrimaryKey
    val storyId: Long,
    val storyTitle: String,
    val author: String,
    val createdAt: DateTime,
    val storyUrl: String
)
