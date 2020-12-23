package com.infinitumcode.hackernews.di

import com.infinitumcode.hackernews.data.api.HackerNewsService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10000L
private const val BASE_URL = "https://hn.algolia.com/api/v1/"

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()).create(HackerNewsService::class.java) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .build()
}
