package com.mcdenny.data.remote.services

import com.mcdenny.domain.models.weather.WeatherDomainModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("weather")
    suspend fun fetchLocationWeather(@QueryMap query: HashMap<String, Any>): WeatherDomainModel
}