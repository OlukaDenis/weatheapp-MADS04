package com.mcdenny.nssfweather.mappers

import com.mcdenny.domain.models.weather.WeatherMainDomainModel
import com.mcdenny.nssfweather.models.TemperatureUiModel
import javax.inject.Inject

class TemperatureUiModelMapper @Inject constructor():
    BaseUiMapper<WeatherMainDomainModel, TemperatureUiModel>{
    override fun toUi(entity: WeatherMainDomainModel): TemperatureUiModel {
        return TemperatureUiModel(
            min = "${entity.temp_min}\u2103",
            max = "${entity.temp_max}\u2103",
            current = "${entity.temp}\u2103",
        )
//        \u2103
    }
}
//min = "${entity.temp_min}°",
//max = "${entity.temp_max}°",
//current = "${entity.temp}°",