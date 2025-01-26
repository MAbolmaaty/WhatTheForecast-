package com.emapps.whattheforecast.data.network

sealed class NetworkResource<T>(
    val data: T?
) {
    class Success<T>(data: T?) : NetworkResource<T>(data)
    class Error<T>(error: T?) : NetworkResource<T>(error)
}