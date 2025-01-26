package com.emapps.whattheforecast.data.repository

import com.emapps.whattheforecast.data.model.ForecastResponseModel
import com.emapps.whattheforecast.data.network.NetworkResource
import com.emapps.whattheforecast.data.network.RetrofitInstance
import javax.inject.Inject

class ForecastRepository @Inject constructor() : BaseRepository() {

    suspend fun fetchForecast(
        key: String,
        city: String,
        days: Int
    ): NetworkResource<ForecastResponseModel> {
        return safeApiCall {
            RetrofitInstance.forecastApi.fetchCityForecast(key, city, days)
        }
    }
}