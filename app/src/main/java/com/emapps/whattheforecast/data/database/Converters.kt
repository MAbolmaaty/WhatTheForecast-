package com.emapps.whattheforecast.data.database

import androidx.room.TypeConverter
import com.emapps.whattheforecast.data.model.CurrentForecast
import com.emapps.whattheforecast.data.model.Forecast
import com.emapps.whattheforecast.data.model.ForecastCondition
import com.emapps.whattheforecast.data.model.ForecastDay
import com.emapps.whattheforecast.data.model.ForecastDayDetails
import com.emapps.whattheforecast.data.model.ForecastLocation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromLocationToString(forecastLocation: ForecastLocation?): String? {
        if (forecastLocation == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(forecastLocation)
    }

    @TypeConverter
    fun fromStringToLocation(locationJson: String?): ForecastLocation? {
        if (locationJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ForecastLocation>() {}.type
        return gson.fromJson(locationJson, type)
    }

    @TypeConverter
    fun fromConditionToString(forecastCondition: ForecastCondition?): String? {
        if (forecastCondition == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(forecastCondition)
    }

    @TypeConverter
    fun fromStringToForecastCondition(conditionJson: String?): ForecastCondition? {
        if (conditionJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ForecastCondition>() {}.type
        return gson.fromJson(conditionJson, type)
    }

    @TypeConverter
    fun fromCurrentToString(currentForecast: CurrentForecast?): String? {
        if (currentForecast == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(currentForecast)
    }

    @TypeConverter
    fun fromStringToCurrent(currentJson: String?): CurrentForecast? {
        if (currentJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<CurrentForecast>() {}.type
        return gson.fromJson(currentJson, type)
    }

    @TypeConverter
    fun fromDayDetailsToString(dayDetails: ForecastDayDetails?): String? {
        if (dayDetails == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(dayDetails)
    }

    @TypeConverter
    fun fromStringToDayDetails(dayDetailsJson: String?): ForecastDayDetails? {
        if (dayDetailsJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ForecastDayDetails>() {}.type
        return gson.fromJson(dayDetailsJson, type)
    }

    @TypeConverter
    fun fromForecastDayList(days: List<ForecastDay>?): String? {
        if (days == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ForecastDay>>() {}.type
        return gson.toJson(days, type)
    }

    @TypeConverter
    fun toForecastDayList(json: String?): List<ForecastDay>? {
        if (json == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ForecastDay>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromForecastToString(forecast: Forecast?): String? {
        if (forecast == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(forecast)
    }

    @TypeConverter
    fun fromStringToForecast(forecastJson: String?): Forecast? {
        if (forecastJson == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Forecast>() {}.type
        return gson.fromJson(forecastJson, type)
    }
}