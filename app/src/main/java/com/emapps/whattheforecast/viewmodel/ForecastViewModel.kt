package com.emapps.whattheforecast.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emapps.whattheforecast.data.database.dao.ForecastDao
import com.emapps.whattheforecast.data.model.ForecastResponseModel
import com.emapps.whattheforecast.data.repository.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {

    @Inject
    lateinit var forecastDao: ForecastDao

    private val _cityForecast = MutableLiveData<ForecastResponseModel?>()
    private val _loading = MutableLiveData<Boolean>()

    fun fetchCityForecast(key: String, city: String, days: Int) {
        viewModelScope.launch {
            _loading.postValue(true)
            _cityForecast.postValue(
                repository.fetchForecast(key, city, days).data
            )
            _loading.postValue(false)
        }
    }

    fun fetchCachedForecast(key: String, city: String, days: Int) {
        viewModelScope.launch {
            val forecast = forecastDao.getForecastById(0)
            if (forecast != null) {
                _cityForecast.postValue(forecast)
                fetchCityForecast(key, forecast.location.name, days)
            } else {
                fetchCityForecast(key, city, days)
            }
        }
    }

    fun observeCityForecast(
        lifecycleOwner: LifecycleOwner,
        observer: (ForecastResponseModel?) -> Unit
    ) {
        _cityForecast.observe(lifecycleOwner) {
            it?.let(observer)
        }
    }

    fun observeLoading(
        lifecycleOwner: LifecycleOwner,
        observer: (Boolean) -> Unit
    ) {
        _loading.observe(lifecycleOwner) {
            it?.let(observer)
        }
    }
}