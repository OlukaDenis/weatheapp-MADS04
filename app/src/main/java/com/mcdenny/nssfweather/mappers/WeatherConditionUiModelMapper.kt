package com.mcdenny.nssfweather.mappers

import com.mcdenny.domain.models.weather.WeatherInfoDomainModel
import com.mcdenny.nssfweather.R
import com.mcdenny.nssfweather.models.WeatherConditionType
import com.mcdenny.nssfweather.models.WeatherConditionUiModel
import javax.inject.Inject

class WeatherConditionUiModelMapper @Inject constructor() :
    BaseUiMapper<WeatherInfoDomainModel, WeatherConditionUiModel>{
    override fun toUi(entity: WeatherInfoDomainModel): WeatherConditionUiModel {
        return WeatherConditionUiModel(
            type = extractConditionType(entity.id),
            name = extractConditionName(entity.id),
            description = entity.description,
            icon = extractConditionIcon(entity.id),
            image = extractConditionImage(entity.id),
            backgroundColor = extractConditionBackground(entity.id)
        )
    }


    private fun extractConditionType(id: Int) : WeatherConditionType =
        when (id) {
            800 -> WeatherConditionType.SUNNY
            in (801..804) -> WeatherConditionType.CLOUDY
            else -> WeatherConditionType.RAINY
        }

    private fun extractConditionName(id: Int) : String =
        when (id) {
            800 -> "Sunny"
            in (801..804) -> "Cloudy"
            else -> "Rainy"
        }

    private fun extractConditionIcon(id: Int) : Int =
        when (id) {
            800 -> R.drawable.clear
            in (801..804) -> R.drawable.partlysunny
            else -> R.drawable.rain
        }

    private fun extractConditionImage(id: Int) : Int =
        when (id) {
            800 -> R.drawable.forest_sunny
            in (801..804) -> R.drawable.forest_cloudy
            else -> R.drawable.forest_rainy
        }

    private fun extractConditionBackground(id: Int) : Int =
        when (id) {
            800 -> R.color.sunny
            in (801..804) -> R.color.cloudy
            else -> R.color.rainy
        }
}
