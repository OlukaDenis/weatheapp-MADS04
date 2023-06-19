package com.mcdenny.domain.models.weather

data class WeatherDomainModel(
    val clouds: CloudsDomainModel,
    val dt: Int,
    val dt_txt: String,
    val main: MainDomainModel,
    val pop: Double,
    val sys: SysDomainModel,
    val visibility: Int,
    val weather: List<WeatherInfoDomainModel>,
    val wind: WindDomainModel
)