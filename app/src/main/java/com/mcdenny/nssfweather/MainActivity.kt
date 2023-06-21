package com.mcdenny.nssfweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.libraries.places.api.Places
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
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_content_main
        ) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener(listener)

        if(!Places.isInitialized()) {
            Places.initialize(this, BuildConfig.PLACES_KEY)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private val  listener = NavController.OnDestinationChangedListener {_, destination, _ ->
        when(destination.id) {
        }
    }


}