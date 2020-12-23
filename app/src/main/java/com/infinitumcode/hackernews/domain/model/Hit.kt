package com.infinitumcode.hackernews.domain.model

import org.joda.time.DateTime

data class Hit (
    val storyId: Long,
    val storyTitle: String,
    val author: String,
    val createdAt: DateTime,
    val storyUrl: String
)