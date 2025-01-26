package com.emapps.whattheforecast.data.repository

import com.emapps.whattheforecast.data.database.dao.ForecastDao
import com.emapps.whattheforecast.data.model.ForecastResponseModel
import com.emapps.whattheforecast.data.network.NetworkResource
import com.emapps.whattheforecast.data.network.RetrofitInstance
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val forecastDao: ForecastDao
) : BaseRepository() {

    suspend fun fetchForecast(
        key: String,
        city: String,
        days: Int
    ): NetworkResource<ForecastResponseModel> {
        val response = safeApiCall {
            RetrofitInstance.forecastApi.fetchCityForecast(key, city, days)
        }
        val forecast = response.data
        forecast?.run {
            forecast.id = 0
            forecastDao.addForecast(forecast)
        }
        return response
    }
}