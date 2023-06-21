package com.mcdenny.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.mcdenny.domain.dispacher.AppDispatcher
import com.mcdenny.domain.models.Resource
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.repository.RemoteRepository
import com.mcdenny.domain.repository.UtilRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchDailyWeatherUseCaseTest {
    @MockK
    lateinit var remote: RemoteRepository

    @MockK
    lateinit var utilRepository: UtilRepository

    private lateinit var fetchDailyWeatherUseCase: FetchDailyWeatherUseCase

    @MockK
    lateinit var dispatcher: AppDispatcher

    val dummy = mutableListOf<WeatherDomainModel>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        fetchDailyWeatherUseCase = FetchDailyWeatherUseCase(dispatcher, remote, utilRepository)
    }

    @Test
    fun `Test fetch daily weather information`() = runBlocking {
        // Given
        coEvery { dispatcher.io } returns Dispatchers.Unconfined
        coEvery { remote.fetchDailyWeatherForecast(any()) } returns dummy

        // When
        val result = fetchDailyWeatherUseCase(FetchDailyWeatherUseCase.Param(0.1, 0.2))

        // Then
        result.collect {
            if(it is Resource.Success) {
                assertThat(it.data).isNotNull()
                assertThat(it.data).isEmpty()
            }
        }
    }
}