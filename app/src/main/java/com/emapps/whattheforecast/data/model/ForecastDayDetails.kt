package com.emapps.whattheforecast.data.model

import com.squareup.moshi.Json

data class ForecastDayDetails(
    @field:Json(name = "maxtemp_c") val maxCelsiusTemp: Float,
    @field:Json(name = "mintemp_c") val minCelsiusTemp: Float,
    val condition: ForecastCondition
)
