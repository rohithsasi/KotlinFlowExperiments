package com.nike.userresourcesdemo.repository

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class LocationRepository(context: Context) {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationCallback: LocationCallback? = null

    @ExperimentalCoroutinesApi
    fun requestLocationUpdates(interval: Long): Flow<Location> = channelFlow {
        clear()
        val request = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            this.interval = interval
            fastestInterval = interval
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                result?.lastLocation?.let { offer(it) }
            }
        }.also {
            fusedLocationProviderClient.requestLocationUpdates(request, it, null)
        }
        awaitClose { clear() }
    }

    fun clear() = locationCallback?.let {
        fusedLocationProviderClient.removeLocationUpdates(it)
        locationCallback = null
    }

}
