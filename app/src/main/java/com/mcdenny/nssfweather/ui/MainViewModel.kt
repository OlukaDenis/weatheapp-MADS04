package com.mcdenny.nssfweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mcdenny.domain.models.Resource
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.usecases.FetchLocationWeatherUseCase
import com.mcdenny.nssfweather.mappers.WeatherUiModelMapper
import com.mcdenny.nssfweather.models.WeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchLocationWeatherUseCase: FetchLocationWeatherUseCase,
    private val weatherUiModelMapper: WeatherUiModelMapper
) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private var _currentWeatherState =
        MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Initial)
    val currentWeatherState get() = _currentWeatherState.asLiveData()

    fun getLocationWeather() {
        viewModelScope.launch {
            val param = FetchLocationWeatherUseCase.Param(
                latitude = 0.30166722214457753,
                longitude = 32.646858842178546
            )

            fetchLocationWeatherUseCase(param).collect {
                when (it) {
                    is Resource.Loading -> _currentWeatherState.value = CurrentWeatherState.Loading
                    is Resource.Error -> _currentWeatherState.value =
                        CurrentWeatherState.Error(message = it.exception)

                    is Resource.Success -> _currentWeatherState.value =
                        CurrentWeatherState.Success(data = weatherUiModelMapper.toUi(it.data))
                }
            }
        }
    }

    sealed class CurrentWeatherState {
        object Initial : CurrentWeatherState()
        object Loading : CurrentWeatherState()
        data class Error(val message: String) : CurrentWeatherState()
        data class Success(val data: WeatherUiModel) : CurrentWeatherState()
    }
}