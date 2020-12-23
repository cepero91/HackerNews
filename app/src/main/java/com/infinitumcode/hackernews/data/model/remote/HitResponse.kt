package com.infinitumcode.hackernews.data.model.remote

import com.google.gson.annotations.SerializedName

data class HitResponse(
    @SerializedName("nbHits")
    val nbHits: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("nbPages")
    val nbPages: Int,
    @SerializedName("hitsPerPage")
    val hitsPerPage: Int,
    @SerializedName("query")
    val query: String,
    @SerializedName("hits")
    val hitDtos: List<HitDto> = listOf()
)
