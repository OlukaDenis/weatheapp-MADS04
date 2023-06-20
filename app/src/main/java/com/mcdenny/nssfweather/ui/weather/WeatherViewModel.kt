package com.mcdenny.nssfweather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mcdenny.domain.models.Resource
import com.mcdenny.domain.usecases.FetchDailyWeatherUseCase
import com.mcdenny.domain.usecases.FetchLocationWeatherUseCase
import com.mcdenny.nssfweather.mappers.WeatherUiModelMapper
import com.mcdenny.nssfweather.models.WeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val fetchLocationWeatherUseCase: FetchLocationWeatherUseCase,
    private val fetchDailyWeatherUseCase: FetchDailyWeatherUseCase,
    private val weatherUiModelMapper: WeatherUiModelMapper
) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _currentWeatherState =
        MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Initial)
    val currentWeatherState get() = _currentWeatherState.asLiveData()

    private val _weatherForecastState =
        MutableStateFlow<DailyWeatherState>(DailyWeatherState.Initial)
    val weatherForecastState get() = _weatherForecastState.asLiveData()

    fun getLocationWeather() {
        viewModelScope.launch {
            val param = FetchLocationWeatherUseCase.Param(
                latitude = 3.18,
                longitude = 33.96
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

    fun getDailyWeatherForecast() {
        viewModelScope.launch {
            val param = FetchDailyWeatherUseCase.Param(
                latitude = 3.18,
                longitude = 33.96
            )

            fetchDailyWeatherUseCase(param).collect {
                when (it) {
                    is Resource.Loading -> _weatherForecastState.value = DailyWeatherState.Loading
                    is Resource.Error -> _weatherForecastState.value =
                        DailyWeatherState.Error(message = it.exception)

                    is Resource.Success -> _weatherForecastState.value =
                        DailyWeatherState.Success(data = it.data.map { m -> weatherUiModelMapper.toUi(m) })
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

    sealed class DailyWeatherState {
        object Initial : DailyWeatherState()
        object Loading : DailyWeatherState()
        data class Error(val message: String) : DailyWeatherState()
        data class Success(val data: List<WeatherUiModel>) : DailyWeatherState()
    }
}