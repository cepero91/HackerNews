package com.infinitumcode.hackernews.data.datasource

import com.infinitumcode.hackernews.data.model.remote.HitResponse
import com.infinitumcode.hackernews.data.wrapper.Resource

interface HackerNewsRemoteDataSource {
    suspend fun searchByDate(query: String?, page: Int): Resource<HitResponse>
}
