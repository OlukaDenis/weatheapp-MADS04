package com.mcdenny.nssfweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.mcdenny.domain.models.weather.WeatherInfoDomainModel
import com.mcdenny.nssfweather.databinding.ActivityMainBinding
import com.mcdenny.nssfweather.models.WeatherUiModel
import com.mcdenny.nssfweather.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getLocationWeather()
        observeCurrentWeather()
    }

    private fun observeCurrentWeather() {
        viewModel.currentWeatherState.observe(this) {
            when (it) {
                is MainViewModel.CurrentWeatherState.Error -> {
                    Timber.d("Error: %s", it.message)
                }

                is MainViewModel.CurrentWeatherState.Success -> {
                    Timber.d("Current weather: %s", it.data)
                    populateData(it.data)
                }

                else -> {}
            }
        }
    }

    private fun populateData(data: WeatherUiModel) {
        with(binding) {

            data.condition?.let { condition ->
                root.setBackgroundColor(condition.backgroundColor)
                civWeatherCondition.load(condition.image) {
                    crossfade(true)
                    placeholder(R.drawable.forest_sunny)
                    error(R.drawable.forest_sunny)
                }
                mtvCurrentCondition.text = condition.name
            }

            mtvCurrentTemp.text = data.temperature.current

        }
    }
}