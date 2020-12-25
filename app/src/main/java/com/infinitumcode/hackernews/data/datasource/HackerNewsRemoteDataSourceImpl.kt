package com.infinitumcode.hackernews.data.datasource

import com.infinitumcode.hackernews.data.api.HackerNewsService
import com.infinitumcode.hackernews.data.model.remote.HitResponse
import com.infinitumcode.hackernews.data.networking.RemoteCallHelper
import com.infinitumcode.hackernews.data.wrapper.Resource
import com.infinitumcode.hackernews.utils.DEFAULT_QUERY

class HackerNewsRemoteDataSourceImpl(
    private val service: HackerNewsService,
) : HackerNewsRemoteDataSource, RemoteCallHelper() {

    override suspend fun searchByDate(query: String?, page: Int): Resource<HitResponse> {
        return safeApiCall { service.searchByDate(query ?: DEFAULT_QUERY, page) }
    }
}
