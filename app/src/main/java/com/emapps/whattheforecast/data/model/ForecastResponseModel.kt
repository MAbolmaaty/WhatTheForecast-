package com.emapps.whattheforecast.data.model

data class ForecastResponseModel(
    val location: ForecastLocation,
    val current: CurrentForecast,
    val forecast: Forecast
)