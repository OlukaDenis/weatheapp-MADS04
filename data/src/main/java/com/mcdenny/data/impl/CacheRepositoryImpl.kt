package com.mcdenny.data.impl

import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.repository.CacheRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(): CacheRepository {
    override suspend fun saveWeather(weatherDomainModel: WeatherDomainModel) {
        TODO("Not yet implemented")
    }

    override fun getWeatherList(): Flow<List<WeatherDomainModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeatherToFavorite(weatherDomainModel: WeatherDomainModel) {
        TODO("Not yet implemented")
    }

    override fun getWeatherFavoritesList(): Flow<List<WeatherDomainModel>> {
        TODO("Not yet implemented")
    }
}