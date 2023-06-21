package com.mcdenny.data.remote.models.weather

data class WeatherRemoteModel(
    val dt: Int?,
    val dt_txt: String?,
    val main: WeatherMainRemoteModel,
    val pop: Double?,
    val visibility: Int?,
    val weather: List<WeatherInfoRemoteModel>?,
    val wind: WindRemoteModel,
    val name: String?
)