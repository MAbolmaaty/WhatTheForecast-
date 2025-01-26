package com.emapps.whattheforecast.data.repository

import com.emapps.whattheforecast.data.network.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository() {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend() -> Response<T>): NetworkResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    NetworkResource.Success(data = response.body())
                } else {
                    NetworkResource.Error(error = null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                NetworkResource.Error(error = null)
            }
        }
    }
}