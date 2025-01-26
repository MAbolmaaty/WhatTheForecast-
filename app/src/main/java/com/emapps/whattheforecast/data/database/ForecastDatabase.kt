package com.emapps.whattheforecast.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emapps.whattheforecast.data.database.dao.ForecastDao
import com.emapps.whattheforecast.data.model.ForecastResponseModel

const val DATABASE_VERSION = 1

@Database(entities = [ForecastResponseModel::class], version = DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class ForecastDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "ForecastDB"

        fun buildDatabase(context: Context): ForecastDatabase {
            return Room.databaseBuilder(context, ForecastDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun forecastDao(): ForecastDao
}