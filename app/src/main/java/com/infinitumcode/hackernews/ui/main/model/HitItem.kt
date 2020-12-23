package com.infinitumcode.hackernews.ui.main.model

import com.github.marlonlom.utilities.timeago.TimeAgo
import org.joda.time.DateTime
import java.lang.StringBuilder

data class HitItem(
    val storyId: Long,
    val storyTitle: String,
    val author: String,
    val createdAt: DateTime,
    val storyUrl: String
) {
    fun humanAuthorAndCreatedAtDate(): String {
        return StringBuilder().append(author).append("-").append(TimeAgo.using(createdAt.millis))
            .toString()
    }
}