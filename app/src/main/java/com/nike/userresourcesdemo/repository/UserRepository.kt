package com.nike.userresourcesdemo.repository

import com.nike.userresourcesdemo.model.User
import com.nike.userresourcesdemo.network.RestClient.userApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UserRepository {

    suspend fun getAllUsers(): List<User> = withContext(IO) {
        userApi.getAllUsers().sortedBy { it.name }
    }

    suspend fun getUsersByZipcode(zipcode: String): List<User> = withContext(IO) {
        userApi.getUsersByZipcode(zipcode).sortedBy { it.name }
    }
}