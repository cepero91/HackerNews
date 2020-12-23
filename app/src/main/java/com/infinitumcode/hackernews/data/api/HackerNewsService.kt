package com.infinitumcode.hackernews.data.api

import com.infinitumcode.hackernews.data.model.remote.HitResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsService {

    @GET("search_by_date")
    suspend fun searchByDate(@Query("page") page: Int, @Query("query") query: String): HitResponse

}