package com.emapps.whattheforecast.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ForecastApp : Application() {
    companion object {
        private lateinit var instance: ForecastApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}