package com.nike.userresourcesdemo.model

import com.squareup.moshi.Json

data class Todo(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "completed") val completed: Boolean
)