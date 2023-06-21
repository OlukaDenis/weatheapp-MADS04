package com.mcdenny.data.remote.mappers

import com.mcdenny.data.remote.models.weather.WeatherMainRemoteModel
import com.mcdenny.domain.models.weather.WeatherMainDomainModel
import javax.inject.Inject

class WeatherMainRemoteMapper @Inject constructor() :
    BaseRemoteMapper<WeatherMainRemoteModel, WeatherMainDomainModel> {
    override fun toDomain(entity: WeatherMainRemoteModel): WeatherMainDomainModel {
        return WeatherMainDomainModel(
            feels_like = entity.feels_like ?: 0.0,
            grnd_level = entity.grnd_level ?: 0,
            humidity = entity.humidity ?: 0,
            pressure = entity.pressure ?: 0,
            sea_level = entity.sea_level ?: 0,
            temp = entity.temp ?: 0.0,
            temp_kf = entity.temp_kf ?: 0.0,
            temp_max = entity.temp_max ?: 0.0,
            temp_min = entity.temp_min ?: 0.0,
        )
    }
}