package com.nike.userresourcesdemo.viewmodel.old

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nike.userresourcesdemo.model.User
import com.nike.userresourcesdemo.repository.UserRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


@Deprecated("Use improved version com.nike.userresourcesdemo.viewmodel.UserViewModel")
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository()
    private val _users = MutableLiveData<Result<List<User>>>()

    val users: LiveData<Result<List<User>>>
        get() = _users

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _users.postValue(Result.failure(e))
    }

    fun getAllUsers() = viewModelScope.launch(exceptionHandler) {
        _users.value = Result.success(repository.getAllUsers())
    }

    fun getUsersByZipcode(zipcode: String) = viewModelScope.launch(exceptionHandler) {
        _users.postValue(Result.success(repository.getUsersByZipcode(zipcode)))
    }
}