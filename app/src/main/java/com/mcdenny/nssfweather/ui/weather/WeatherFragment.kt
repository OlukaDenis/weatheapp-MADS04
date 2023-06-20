package com.mcdenny.nssfweather.ui.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.mcdenny.nssfweather.R
import com.mcdenny.nssfweather.base.BaseFragment
import com.mcdenny.nssfweather.databinding.FragmentWeatherBinding
import com.mcdenny.nssfweather.models.WeatherUiModel
import com.mcdenny.nssfweather.ui.MainViewModel
import com.mcdenny.nssfweather.ui.adapters.WeatherListAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate) {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                layoutManager = LinearLayoutManager(requireContext())
            }

            observeDailyWeather(weatherAdapter)
        }
    }

    private fun observeCurrentWeather() {
        viewModel.currentWeatherState.observe(viewLifecycleOwner) {
            when (it) {
                is WeatherViewModel.CurrentWeatherState.Error -> {
                    Timber.d("Error: %s", it.message)
                }

                is WeatherViewModel.CurrentWeatherState.Success -> {
                    populateData(it.data)
                }

                else -> {}
            }
        }
    }

    private fun observeDailyWeather(adapter: WeatherListAdapter) {
        viewModel.weatherForecastState.observe(viewLifecycleOwner) {
            when (it) {
                is WeatherViewModel.DailyWeatherState.Error -> {
                    Timber.d("Error: %s", it.message)
                }

                is WeatherViewModel.DailyWeatherState.Success -> {
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
                    kotlin.error(R.drawable.forest_sunny)
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