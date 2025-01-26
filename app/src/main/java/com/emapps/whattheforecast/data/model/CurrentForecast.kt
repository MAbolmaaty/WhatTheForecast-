package com.emapps.whattheforecast.data.model

import com.squareup.moshi.Json

data class CurrentForecast (
    @field:Json(name = "temp_c") val tempInCelsius: Float,
    val condition: ForecastCondition
)