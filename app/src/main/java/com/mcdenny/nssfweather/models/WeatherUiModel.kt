package com.mcdenny.nssfweather.models

data class WeatherUiModel(
    val locationName: String,
    val temperature: TemperatureUiModel,
    val condition: WeatherConditionUiModel?
)