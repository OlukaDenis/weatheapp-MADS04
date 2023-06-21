package com.mcdenny.domain.repository

import com.mcdenny.domain.models.weather.WeatherDomainModel
import kotlinx.coroutines.flow.Flow

interface CacheRepository {
    suspend fun saveWeather(weatherDomainModel: WeatherDomainModel)
    fun getWeatherList(): Flow<List<WeatherDomainModel>>

    suspend fun saveWeatherToFavorite(weatherDomainModel: WeatherDomainModel)
    fun getWeatherFavoritesList(): Flow<List<WeatherDomainModel>>
}