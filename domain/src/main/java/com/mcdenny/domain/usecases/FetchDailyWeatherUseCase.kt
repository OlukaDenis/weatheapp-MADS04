package com.mcdenny.domain.usecases

import com.mcdenny.domain.base.BaseFlowUseCase
import com.mcdenny.domain.dispacher.AppDispatcher
import com.mcdenny.domain.models.Resource
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.repository.RemoteRepository
import com.mcdenny.domain.repository.UtilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class FetchDailyWeatherUseCase @Inject constructor(
    dispatcher: AppDispatcher,
    private val remote: RemoteRepository,
    private val utilRepository: UtilRepository
) : BaseFlowUseCase<FetchDailyWeatherUseCase.Param, Resource<List<WeatherDomainModel>>>(dispatcher) {

    data class Param(
        val longitude: Double,
        val latitude: Double
    )

    override fun run(param: Param?): Flow<Resource<List<WeatherDomainModel>>> = flow {
        emit(Resource.Loading)

        try {
            param?.let {
                val request = HashMap<String, Any>().apply {
                    this["lat"] = it.latitude
                    this["lon"] = it.longitude
                    this["units"] = "metric"
                    this["appid"] = "269c433647c44e7f08da7c96212b5f57"
                }

                val weather = remote.fetchDailyWeatherForecast(request)
                val list = weather.filter { it.dt_txt.contains("12:00:00") }
                emit(Resource.Success(list))
            }
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            emit(Resource.Error(utilRepository.getNetworkError(throwable)))
        }
    }
}