package com.nike.userresourcesdemo.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nike.userresourcesdemo.extension.launchAndCollect
import com.nike.userresourcesdemo.repository.LocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LocationRepository(application)
    private val _currentLocation = MutableLiveData<Result<Location>>()

    val currentLocation: LiveData<Result<Location>>
        get() = _currentLocation

    @ExperimentalCoroutinesApi
    fun requestLocationUpdates(interval: Long = 5_000) = viewModelScope.launchAndCollect(_currentLocation) {
        repository.requestLocationUpdates(interval)
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }
}