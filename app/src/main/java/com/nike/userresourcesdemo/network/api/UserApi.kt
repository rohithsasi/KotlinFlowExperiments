package com.nike.userresourcesdemo.network.api

import com.nike.userresourcesdemo.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/users")
    suspend fun getAllUsers(): List<User>

    @GET("/users")
    suspend fun getUsersByZipcode(
        @Query("address.zipcode") zipcode: String): List<User>
}