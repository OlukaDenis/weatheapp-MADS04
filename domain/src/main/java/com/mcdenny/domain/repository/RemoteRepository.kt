package com.mcdenny.domain.repository

import com.mcdenny.domain.models.weather.WeatherDomainModel

interface RemoteRepository {
    suspend fun fetchLocationWeather(query: HashMap<String, Any>): WeatherDomainModel
    suspend fun fetchWeeklyWeatherForecast(query: HashMap<String, Any>): List<WeatherDomainModel>
}