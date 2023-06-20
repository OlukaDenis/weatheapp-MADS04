package com.mcdenny.data.impl

import com.mcdenny.data.remote.mappers.WeatherRemoteMapper
import com.mcdenny.data.remote.services.ApiService
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val weatherRemoteMapper: WeatherRemoteMapper
): RemoteRepository {

    override suspend fun fetchLocationWeather(query: HashMap<String, Any>): WeatherDomainModel {
        return try {
            val response = service.fetchLocationWeather(query)
            weatherRemoteMapper.toDomain(response)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun fetchDailyWeatherForecast(query: HashMap<String, Any>): List<WeatherDomainModel> {
        return try {
            val response = service.fetchDailyWeather(query).list
            response.map { weatherRemoteMapper.toDomain(it) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }
}