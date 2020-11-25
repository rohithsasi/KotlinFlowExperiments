/*
 * Copyright (c) 2018. Nike Inc. All rights reserved.
 *
 * Nike Retail Consumer Experience
 */

package com.nike.userresourcesdemo.viewmodel.old

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nike.userresourcesdemo.repository.LocationRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Deprecated("Use improved version com.nike.userresourcesdemo.viewmodel.LocationViewModel")
class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LocationRepository(application)
    private val _currentLocation = MutableLiveData<Result<Location>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _currentLocation.postValue(Result.failure(e))
    }

    val currentLocation: LiveData<Result<Location>>
        get() = _currentLocation

    @ExperimentalCoroutinesApi
    fun requestLocationUpdates(interval: Long = 5_000) = viewModelScope.launch(exceptionHandler) {
        repository.requestLocationUpdates(interval).collect {
            _currentLocation.postValue(Result.success(it))
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }
}