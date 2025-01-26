package com.emapps.whattheforecast.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emapps.whattheforecast.data.model.ForecastResponseModel
import com.emapps.whattheforecast.data.network.NetworkResource
import com.emapps.whattheforecast.data.repository.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {

    private val _cityForecast = MutableLiveData<NetworkResource<ForecastResponseModel>>()
    private val _loading = MutableLiveData<Boolean>()

    fun fetchCityForecast(key: String, city: String, days: Int) {
        viewModelScope.launch {
            _loading.postValue(true)
            _cityForecast.postValue(
                repository.fetchForecast(key, city, days)
            )
            _loading.postValue(false)
        }
    }

    fun observeCityForecast(
        lifecycleOwner: LifecycleOwner,
        observer: (NetworkResource<ForecastResponseModel>) -> Unit
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