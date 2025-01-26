package com.emapps.whattheforecast.data.model

import com.squareup.moshi.Json

data class Forecast(
    @field:Json(name = "forecastday") val forecastDay: List<ForecastDay>
)
