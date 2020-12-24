package com.infinitumcode.hackernews.ui.main.mapper

import com.infinitumcode.hackernews.data.mapper.Mapper
import com.infinitumcode.hackernews.domain.model.Hit
import com.infinitumcode.hackernews.ui.main.model.HitItem

class MapHitToItem : Mapper<Hit, HitItem> {
    override fun map(from: Hit): HitItem {
        return HitItem(
            from.objectId,
            from.storyTitle ?: "",
            from.author,
            from.createdAt,
            from.storyUrl ?: ""
        )
    }
}