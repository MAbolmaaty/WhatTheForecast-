package com.emapps.whattheforecast

import com.emapps.whattheforecast.data.repository.ForecastRepository
import com.emapps.whattheforecast.viewmodel.ForecastViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class ForecastViewModelTest {
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var forecastRepository: ForecastRepository

    @Before
    fun setup() {
        forecastRepository = mock(ForecastRepository::class.java)
        forecastViewModel = ForecastViewModel(forecastRepository)
    }

    @Test
    fun `when ViewModel fetchCityForecast is called, forecast data is updated`() {

    }
}