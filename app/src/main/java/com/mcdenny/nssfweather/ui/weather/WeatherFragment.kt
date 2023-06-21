package com.mcdenny.nssfweather.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.mcdenny.nssfweather.R
import com.mcdenny.nssfweather.base.BaseFragment
import com.mcdenny.nssfweather.databinding.FragmentWeatherBinding
import com.mcdenny.nssfweather.models.WeatherUiModel
import com.mcdenny.nssfweather.ui.adapters.WeatherListAdapter
import com.mcdenny.nssfweather.utils.PermissionUtils
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

//        viewModel.getLocationWeather()
//        viewModel.getDailyWeatherForecast()

        observeCurrentWeather()
        observeDailyWeather()

        binding.civSearch.setOnClickListener { openPlaces() }
    }

    private fun openPlaces() {
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .build(requireContext())
        startAutocomplete.launch(intent)
    }

    private val startAutocomplete =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data

                intent?.let {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    val latLong = place.latLng

                    latLong?.let { coord ->

                        viewModel.getLocationWeather(coord.latitude, coord.longitude)
                        viewModel.getDailyWeatherForecast(coord.latitude, coord.longitude)
                    }
                    Timber.d("Place: %s", Autocomplete.getPlaceFromIntent(intent))
                }
            }
        }

    private fun observeCurrentWeather() {
        with(binding) {
            viewModel.currentWeatherState.observe(viewLifecycleOwner) {
                layoutInfo.root.isVisible = it is WeatherViewModel.CurrentWeatherState.Success
                pbLoading.isVisible = it is WeatherViewModel.CurrentWeatherState.Loading

                when (it) {
                    is WeatherViewModel.CurrentWeatherState.Success -> {
                        populateData(it.data)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun observeDailyWeather() {
        with(binding) {
            viewModel.weatherForecastState.observe(viewLifecycleOwner) {
                pbLoading.isVisible = it is WeatherViewModel.DailyWeatherState.Loading

                if (it is WeatherViewModel.DailyWeatherState.Success) {
                    val weatherAdapter = WeatherListAdapter()
                    rvForecast.apply {
                        adapter = weatherAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                    weatherAdapter.submitList(it.data)
                }
            }
        }
    }

    private fun populateData(data: WeatherUiModel) {
        with(binding) {

            mtvLocation.text = data.locationName

            data.condition?.let { condition ->
                root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        condition.backgroundColor
                    )
                )
                layoutInfo.root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        condition.backgroundColor
                    )
                )
                rvForecast.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        condition.backgroundColor
                    )
                )

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

    override fun onStart() {
        super.onStart()
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        binding.pbLoading.isVisible = true
        when {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                setUpLocationListener()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showToast(getString(R.string.please_allow_the_location_permission_for_the_to_work_properly))
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                binding.pbLoading.isVisible = false
                setUpLocationListener()
            } else {
                showToast(getString(R.string.please_allow_the_location_permission_for_the_to_work_properly))
            }
        }

    @SuppressLint("MissingPermission")
    private fun setUpLocationListener() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.getCurrentLocation(
            PRIORITY_HIGH_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(listener: OnTokenCanceledListener) =
                    CancellationTokenSource().token

                override fun isCancellationRequested() = false
            }).addOnSuccessListener {
            if (it != null) {
                viewModel.getLocationWeather(it.latitude, it.longitude)
                viewModel.getDailyWeatherForecast(it.latitude, it.longitude)
            }
        }
    }
}