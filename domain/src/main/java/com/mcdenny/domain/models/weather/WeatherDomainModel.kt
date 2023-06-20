package com.mcdenny.domain.models.weather

data class WeatherDomainModel(
    val dt: Int,
    val dt_txt: String,
    val main: WeatherMainDomainModel,
    val pop: Double,
    val visibility: Int,
    val weather: List<WeatherInfoDomainModel>,
    val name: String
)