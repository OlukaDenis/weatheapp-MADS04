package com.mcdenny.data.remote.mappers

import com.mcdenny.data.remote.models.weather.WeatherInfoRemoteModel
import com.mcdenny.domain.models.weather.WeatherInfoDomainModel
import javax.inject.Inject

class WeatherInfoRemoteMapper @Inject constructor():
    BaseRemoteMapper<WeatherInfoRemoteModel, WeatherInfoDomainModel> {
    override fun toDomain(entity: WeatherInfoRemoteModel): WeatherInfoDomainModel {
        return WeatherInfoDomainModel(
            icon = entity.icon.orEmpty(),
            description = entity.description.orEmpty(),
            id = entity.id ?: 0,
            main = entity.main.orEmpty()
        )
    }
}