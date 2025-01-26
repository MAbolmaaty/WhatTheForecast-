package com.emapps.whattheforecast.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emapps.whattheforecast.data.database.DatabaseConstants.FORECAST_TABLE_NAME
import com.emapps.whattheforecast.data.model.ForecastResponseModel

@Dao
interface ForecastDao {

    @Query("SELECT * FROM $FORECAST_TABLE_NAME WHERE id = :id")
    suspend fun getForecastById(id: Int): ForecastResponseModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addForecast(forecast: ForecastResponseModel)
}