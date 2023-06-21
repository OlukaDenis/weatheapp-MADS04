package com.mcdenny.nssfweather.models

data class WeatherConditionUiModel(
    val type: WeatherConditionType,
    val name: String,
    val description: String,
    val icon: Int,
    val image: Int,
    val backgroundColor: Int
)
