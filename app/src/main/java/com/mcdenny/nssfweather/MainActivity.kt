package com.mcdenny.nssfweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.mcdenny.nssfweather.databinding.ActivityMainBinding
import com.mcdenny.nssfweather.models.WeatherUiModel
import com.mcdenny.nssfweather.ui.MainViewModel
import com.mcdenny.nssfweather.ui.adapters.WeatherListAdapter
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
        viewModel.getDailyWeatherForecast()

        setUpUI()
        observeCurrentWeather()
    }

    private fun setUpUI() {
        with(binding) {
            val weatherAdapter = WeatherListAdapter()
            rvForecast.apply {
                adapter = weatherAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            observeDailyWeather(weatherAdapter)
        }
    }

    private fun observeCurrentWeather() {
        viewModel.currentWeatherState.observe(this) {
            when (it) {
                is MainViewModel.CurrentWeatherState.Error -> {
                    Timber.d("Error: %s", it.message)
                }

                is MainViewModel.CurrentWeatherState.Success -> {
                    populateData(it.data)
                }

                else -> {}
            }
        }
    }

    private fun observeDailyWeather(adapter: WeatherListAdapter) {
        viewModel.weatherForecastState.observe(this) {
            when (it) {
                is MainViewModel.DailyWeatherState.Error -> {
                    Timber.d("Error: %s", it.message)
                }

                is MainViewModel.DailyWeatherState.Success -> {
                    Timber.d("Daily list: %s", it.data)
                    adapter.submitList(it.data)
                }

                else -> {}
            }
        }
    }

    private fun populateData(data: WeatherUiModel) {
        with(binding) {

            mtvLocation.text = data.locationName

            data.condition?.let { condition ->
                root.setBackgroundColor(condition.backgroundColor)
                civWeatherCondition.load(condition.image) {
                    crossfade(true)
                    placeholder(R.drawable.forest_sunny)
                    error(R.drawable.forest_sunny)
                }
                mtvCurrentCondition.text = condition.name
            }

            data.temperature.let {
                mtvCurrentTemp.text = it.current

                with(layoutInfo) {
                    mtvCurrentTemp.text = it.current
                    mtvMaxTemp.text = it.max
                    mtvMinTemp.text = it.min
                }
            }

        }
    }
}