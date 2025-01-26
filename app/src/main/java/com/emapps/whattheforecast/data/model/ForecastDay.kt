package com.emapps.whattheforecast.data.model

import com.squareup.moshi.Json

data class ForecastDay(
    val date: String,
    @field:Json(name = "day") val dayDetails: ForecastDayDetails
)
