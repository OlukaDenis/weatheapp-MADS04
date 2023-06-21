package com.mcdenny.domain.usecases

import com.google.common.truth.Truth
import com.mcdenny.domain.dispacher.AppDispatcher
import com.mcdenny.domain.models.Resource
import com.mcdenny.domain.models.weather.WeatherDomainModel
import com.mcdenny.domain.models.weather.WeatherMainDomainModel
import com.mcdenny.domain.repository.RemoteRepository
import com.mcdenny.domain.repository.UtilRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchLocationWeatherUseCaseTest {
    @MockK
    lateinit var remote: RemoteRepository

    @MockK
    lateinit var utilRepository: UtilRepository

    private lateinit var fetchLocationWeatherUseCase: FetchLocationWeatherUseCase

    @MockK
    lateinit var dispatcher: AppDispatcher

    val dummy = WeatherDomainModel(
        dt = 2,
        main = WeatherMainDomainModel(0.0,0,0,0,0,0.0,0.0,0.0, 0.0),
        dt_txt = "",
        name = "Kampala",
        pop = 0.0,
        visibility = 0,
        weather = emptyList()
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        fetchLocationWeatherUseCase = FetchLocationWeatherUseCase(dispatcher, remote, utilRepository)
    }

    @Test
    fun `Test fetch current weather`() = runBlocking {
        // Given
        coEvery { dispatcher.io } returns Dispatchers.Unconfined
        coEvery { remote.fetchLocationWeather(any()) } returns dummy

        // When
        val result = fetchLocationWeatherUseCase(FetchLocationWeatherUseCase.Param(0.1, 0.2))

        // Then
        result.collect {
            if(it is Resource.Success) {
                Truth.assertThat(it.data).isNotNull()
                Truth.assertThat(it.data.name).isEqualTo("Kampala")
            }
        }
    }
}
