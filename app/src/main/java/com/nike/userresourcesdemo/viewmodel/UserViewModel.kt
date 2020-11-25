package com.nike.userresourcesdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nike.userresourcesdemo.extension.launch
import com.nike.userresourcesdemo.model.User
import com.nike.userresourcesdemo.repository.UserRepository


class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository()
    private val _users = MutableLiveData<Result<List<User>>>()

    val users: LiveData<Result<List<User>>>
        get() =  _users

    fun getAllUsers() = viewModelScope.launch(_users) {
        repository.getAllUsers()
    }

    fun getUsersByZipcode(zipcode: String) = viewModelScope.launch(_users) {
        repository.getUsersByZipcode(zipcode)
    }
}