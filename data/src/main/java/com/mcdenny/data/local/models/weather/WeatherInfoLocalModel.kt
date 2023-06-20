package com.mcdenny.data.local.models.weather

data class WeatherInfoLocalModel(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)