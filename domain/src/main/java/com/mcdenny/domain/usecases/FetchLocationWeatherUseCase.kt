package com.mcdenny.domain.usecases

import com.mcdenny.domain.base.BaseFlowUseCase
import com.mcdenny.domain.dispacher.AppDispatcher
import com.mcdenny.domain.models.Resource
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.repository.RemoteRepository
import com.mcdenny.domain.repository.UtilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchLocationWeatherUseCase @Inject constructor(
    dispatcher: AppDispatcher,
    private val remote: RemoteRepository,
    private val utilRepository: UtilRepository
): BaseFlowUseCase<FetchLocationWeatherUseCase.Param, Resource<WeatherDomainModel>>(dispatcher){

    data class Param(
        val longitude: Double,
        val latitude: Double
    )

    override fun run(param: Param?): Flow<Resource<WeatherDomainModel>> = flow {
        emit(Resource.Loading)

        try {
            param?.let {
                val request = HashMap<String, Any>().apply {
                    this["lat"] = it.latitude
                    this["lon"] = it.longitude
                    this["units"] = "metric"
                    this["appid"] = "269c433647c44e7f08da7c96212b5f57"
                }

                val weather = remote.fetchLocationWeather(request)

                emit(Resource.Success(weather))
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(utilRepository.getNetworkError(throwable)))
        }
    }


}