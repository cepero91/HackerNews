package com.infinitumcode.hackernews.data.model.remote

import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("story_id")
    val storyId: Long,
    @SerializedName("story_title")
    val storyTitle: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("story_url")
    val storyUrl: String
)
