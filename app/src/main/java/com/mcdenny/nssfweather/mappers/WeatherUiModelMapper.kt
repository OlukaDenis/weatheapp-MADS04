package com.mcdenny.nssfweather.mappers

import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.nssfweather.models.WeatherUiModel
import javax.inject.Inject

class WeatherUiModelMapper @Inject constructor(
    private val temperatureUiModelMapper: TemperatureUiModelMapper,
    private val conditionUiModelMapper: WeatherConditionUiModelMapper
):
    BaseUiMapper<WeatherDomainModel, WeatherUiModel> {
    override fun toUi(entity: WeatherDomainModel): WeatherUiModel {
        return WeatherUiModel(
            locationName = entity.name,
            temperature = temperatureUiModelMapper.toUi(entity.main),
            condition = if (entity.weather.isEmpty()) null else conditionUiModelMapper.toUi(entity.weather[0])
        )
    }


}