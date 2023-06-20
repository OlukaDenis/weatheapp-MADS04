package com.mcdenny.data.remote.models

import com.mcdenny.data.remote.models.weather.WeatherRemoteModel

data class WeatherListRemoteResponse(
    val list: List<WeatherRemoteModel>
)