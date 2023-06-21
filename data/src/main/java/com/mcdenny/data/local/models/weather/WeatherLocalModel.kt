package com.mcdenny.data.local.models.weather

data class WeatherLocalModel(
    val dt: Int,
    val dt_txt: String,
    val main: WeatherMainLocalModel,
    val pop: Double,
    val visibility: Int,
    val weather: List<WeatherInfoLocalModel>,
    val wind: WindLocalModel,
    val name: String
)