package com.mcdenny.nssfweather.mappers

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.nssfweather.models.WeatherUiModel
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class WeatherUiModelMapper @Inject constructor(
    private val temperatureUiModelMapper: TemperatureUiModelMapper,
    private val conditionUiModelMapper: WeatherConditionUiModelMapper
):
    BaseUiMapper<WeatherDomainModel, WeatherUiModel> {
    @SuppressLint("NewApi")
    override fun toUi(entity: WeatherDomainModel): WeatherUiModel {
        return WeatherUiModel(
            id = entity.dt,
            locationName = entity.name,
            temperature = temperatureUiModelMapper.toUi(entity.main),
            condition = if (entity.weather.isEmpty()) null else conditionUiModelMapper.toUi(entity.weather[0]),
            day = extractDay(entity.dt_txt)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun extractDay(date: String): String =
       try {
           val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
           val ld = LocalDate.parse(date, formatter)
            val day = ld.dayOfWeek.name
           day.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
       } catch (e: Exception) {
           ""
       }


}