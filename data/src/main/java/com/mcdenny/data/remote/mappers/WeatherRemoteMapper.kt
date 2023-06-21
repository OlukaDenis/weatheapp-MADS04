package com.mcdenny.data.remote.mappers

import com.mcdenny.data.remote.models.weather.WeatherRemoteModel
import com.mcdenny.domain.models.weather.WeatherDomainModel
import javax.inject.Inject

class WeatherRemoteMapper @Inject constructor(
    private val weatherInfoRemoteMapper: WeatherInfoRemoteMapper,
    private val weatherMainRemoteMapper: WeatherMainRemoteMapper
) : BaseRemoteMapper<WeatherRemoteModel, WeatherDomainModel> {
    override fun toDomain(entity: WeatherRemoteModel): WeatherDomainModel {
        return WeatherDomainModel(
            dt = entity.dt ?: 0,
            dt_txt = entity.dt_txt.orEmpty(),
            main = weatherMainRemoteMapper.toDomain(entity.main),
            pop = entity.pop ?: 0.0,
            visibility = entity.visibility ?: 0,
            weather = entity.weather?.map { weatherInfoRemoteMapper.toDomain(it) }.orEmpty(),
            name = entity.name.orEmpty()
        )
    }

}