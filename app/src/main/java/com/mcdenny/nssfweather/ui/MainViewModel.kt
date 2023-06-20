package com.mcdenny.nssfweather.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcdenny.domain.models.Resource
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.usecases.FetchLocationWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchLocationWeatherUseCase: FetchLocationWeatherUseCase
) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private var _currentWeatherState = MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Initial)
    val currentWeatherState get() = _currentWeatherState

    fun getLocationWeather() {
        viewModelScope.launch {
            val param = FetchLocationWeatherUseCase.Param(
                latitude = 0.30166722214457753,
                longitude = 32.646858842178546
            )

            fetchLocationWeatherUseCase(param).collect {
                when (it) {
                    is Resource.Loading -> {
                        Log.d(TAG, "Loading")
                    }
                    is Resource.Error -> {
                        Log.e(TAG, it.exception)
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "Success: ${it.data}")
                    }
                }
            }
        }
    }

    sealed class CurrentWeatherState {
        object Initial: CurrentWeatherState()
        object Loading: CurrentWeatherState()
        data class Error(val message: String): CurrentWeatherState()
        data class Success(val data: WeatherDomainModel): CurrentWeatherState()
    }
}