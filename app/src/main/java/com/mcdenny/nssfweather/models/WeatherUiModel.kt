package com.mcdenny.nssfweather.models

data class WeatherUiModel(
    val id: Int,
    val locationName: String,
    val temperature: TemperatureUiModel,
    val condition: WeatherConditionUiModel?,
    val day: String
)