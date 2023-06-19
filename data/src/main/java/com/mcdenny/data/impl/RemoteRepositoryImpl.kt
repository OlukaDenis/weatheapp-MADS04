package com.mcdenny.data.impl

import com.mcdenny.data.remote.services.ApiService
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: ApiService
): RemoteRepository {

    override suspend fun fetchLocationWeather(query: HashMap<String, Any>): WeatherDomainModel {
        return try {
            val response = service.fetchLocationWeather(query)
//            remoteUserMapper.mapToDomain(response)
            response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun fetchWeeklyWeatherForecast(query: HashMap<String, Any>): List<WeatherDomainModel> {
        TODO("Not yet implemented")
    }
}