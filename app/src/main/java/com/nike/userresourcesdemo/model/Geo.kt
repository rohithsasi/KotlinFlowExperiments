package com.nike.userresourcesdemo.model

import com.squareup.moshi.Json

data class Geo(
    @field:Json(name = "lat") val latitude: Double,
    @field:Json(name = "lng") val longitude: Double
)