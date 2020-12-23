package com.infinitumcode.hackernews.data.mapper

import com.infinitumcode.hackernews.data.model.local.HitEntity
import com.infinitumcode.hackernews.data.model.remote.HitDto
import com.infinitumcode.hackernews.domain.model.Hit
import com.infinitumcode.hackernews.utils.DateUtil

class MapHitDtoToEntity : Mapper<HitDto, HitEntity> {
    override fun map(from: HitDto): HitEntity {
        return HitEntity(
            from.storyId,
            from.storyTitle,
            from.author,
            DateUtil.fromStringToDateTime(from.createdAt),
            from.storyUrl
        )
    }
}

class MapHitEntityToDomain : Mapper<HitEntity, Hit> {
    override fun map(from: HitEntity): Hit {
        return Hit(from.storyId, from.storyTitle, from.author, from.createdAt, from.storyUrl)
    }

}