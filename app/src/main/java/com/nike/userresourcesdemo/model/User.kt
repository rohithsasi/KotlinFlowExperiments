package com.nike.userresourcesdemo.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "address") val address: Address,
    @field:Json(name = "phone") val phone: String
)