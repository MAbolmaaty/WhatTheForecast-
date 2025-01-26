package com.emapps.whattheforecast.data.network

import com.emapps.whattheforecast.data.model.ForecastResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ForecastApi {

    @Headers("Accept: application/json")
    @GET("forecast.json")
    suspend fun fetchCityForecast(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int,
    ): Response<ForecastResponseModel>
}