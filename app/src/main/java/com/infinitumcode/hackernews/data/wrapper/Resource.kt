package com.infinitumcode.hackernews.data.wrapper

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String? = null) : Resource<Nothing>()
}
