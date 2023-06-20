package com.mcdenny.nssfweather.ui.weather

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
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
    private val fields = listOf(
        Place.Field.ID,
        Place.Field.NAME,
        Place.Field.LAT_LNG,
        Place.Field.ADDRESS
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.longitude = 0.34
        viewModel.latitude = 31.89

        viewModel.getLocationWeather()
        viewModel.getDailyWeatherForecast()

        setUpUI()
        observeCurrentWeather()

        binding.civSearch.setOnClickListener{openPlaces()}
    }

    private fun openPlaces() {
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(requireContext())
        startAutocomplete.launch(intent)
    }

    private val startAutocomplete =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
            if(result.resultCode == Activity.RESULT_OK) {
                val intent = result.data

                intent?.let {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    val latLong = place.latLng

                    latLong?.let { lt ->
                        viewModel.latitude = lt.latitude
                        viewModel.longitude = lt.longitude

                        viewModel.getLocationWeather()
                    }
                    Timber.d("Place: %s", Autocomplete.getPlaceFromIntent(intent))
                }
            }
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