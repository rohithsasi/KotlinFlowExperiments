package com.nike.userresourcesdemo.model

import com.squareup.moshi.Json

data class Address(
    @field:Json(name = "street") val street: String,
    @field:Json(name = "suite") val suite: String,
    @field:Json(name = "city") val city: String,
    @field:Json(name = "zipcode") val zipcode: String,
    @field:Json(name = "geo") val geo: Geo
)