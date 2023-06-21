package com.mcdenny.nssfweather.mappers

import com.mcdenny.domain.models.weather.WeatherMainDomainModel
import com.mcdenny.nssfweather.models.TemperatureUiModel
import javax.inject.Inject
import kotlin.math.roundToInt

class TemperatureUiModelMapper @Inject constructor():
    BaseUiMapper<WeatherMainDomainModel, TemperatureUiModel>{
    override fun toUi(entity: WeatherMainDomainModel): TemperatureUiModel {
        return TemperatureUiModel(
            min = "${entity.temp_min.roundToInt()} \u2103",
            max = "${entity.temp_max.roundToInt()} \u2103",
            current = "${entity.temp.roundToInt()} \u2103",
        )
//        \u2103
    }
}
//min = "${entity.temp_min}°",
//max = "${entity.temp_max}°",
//current = "${entity.temp}°",