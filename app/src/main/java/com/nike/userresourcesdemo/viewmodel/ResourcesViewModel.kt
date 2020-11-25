package com.nike.userresourcesdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nike.userresourcesdemo.extension.launch
import com.nike.userresourcesdemo.model.Resources
import com.nike.userresourcesdemo.repository.ResourcesRepository


class ResourcesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ResourcesRepository()
    private val _resources = MutableLiveData<Result<Resources>>()

    val resources: LiveData<Result<Resources>>
        get() =  _resources

    fun getResourcesByUserId(userId: Int) = viewModelScope.launch(_resources) {
        repository.getResourcesByUserId(userId)
    }
}