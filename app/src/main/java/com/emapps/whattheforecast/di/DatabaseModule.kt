package com.emapps.whattheforecast.di

import android.content.Context
import com.emapps.whattheforecast.data.database.ForecastDatabase
import com.emapps.whattheforecast.data.database.dao.ForecastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideForecastDatabase(@ApplicationContext appContext: Context): ForecastDatabase {
        return ForecastDatabase.buildDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideForecastDao(database: ForecastDatabase): ForecastDao {
        return database.forecastDao()
    }
}