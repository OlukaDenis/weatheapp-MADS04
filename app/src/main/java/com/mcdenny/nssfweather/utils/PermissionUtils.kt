package com.mcdenny.nssfweather.utils

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    fun requestLocationPermission(activity: Activity, requestId: Int) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(ACCESS_FINE_LOCATION),
            requestId
        )
    }

    fun isLocationGranted(context: Context): Boolean {
        return ContextCompat
            .checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
    }

//    fun isLocationEnabled(context: Context): Boolean {
//        val gfgLocationManager: GfgLocationManager =
//            context.getSystemService(Context.LOCATION_SERVICE) as GfgLocationManager
//        return gfgLocationManager.isProviderEnabled(GfgLocationManager.GPS_PROVIDER)
//                || gfgLocationManager.isProviderEnabled(GfgLocationManager.NETWORK_PROVIDER)
//    }
}