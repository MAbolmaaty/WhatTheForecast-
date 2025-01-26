package com.emapps.whattheforecast.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emapps.whattheforecast.data.database.DatabaseConstants.FORECAST_TABLE_NAME

@Entity(tableName = FORECAST_TABLE_NAME)
data class ForecastResponseModel(
    @PrimaryKey
    var id: Int,
    val location: ForecastLocation,
    val current: CurrentForecast,
    val forecast: Forecast
)