package com.mcdenny.domain.models.weather

data class WeatherInfoDomainModel(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)