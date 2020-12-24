package com.infinitumcode.hackernews.data.networking

import com.infinitumcode.hackernews.data.wrapper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class RemoteCallHelper {
    companion object {
        const val NO_INTERNET_ERROR = "Oops!! your internet is lost"
        const val GENERIC_ERROR = "Oops!! something's wrong"
        const val CLIENT_ERROR = "Oops!! we can't resolve your request"
        const val TIMEOUT_ERROR = "Oops!! internet to slow"
        const val SERVER_ERROR = "Oops!! something's wrong in our server, try later"
    }

    protected suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val result = when (throwable.code()) {
                            in 400..451 -> error(CLIENT_ERROR)
                            in 500..599 -> error(SERVER_ERROR)
                            else -> error(GENERIC_ERROR)
                        }
                        result
                    }
                    is ConnectException -> error(NO_INTERNET_ERROR)
                    is UnknownHostException -> error(NO_INTERNET_ERROR)
                    is SocketTimeoutException -> error(TIMEOUT_ERROR)
                    is IOException -> error(throwable.message)
                    else -> error(throwable.message)
                }
            }
        }
    }

    private fun error(errorMessage: String?): Resource.Error {
        return Resource.Error(errorMessage)
    }
}
