package com.nike.userresourcesdemo.network.api

import com.nike.userresourcesdemo.model.Album
import com.nike.userresourcesdemo.model.Post
import com.nike.userresourcesdemo.model.Todo
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {

    @GET("/todos")
    suspend fun getAllTodos(): List<Todo>

    @GET("/todos")
    suspend fun getTodosByUserId(
        @Query("userId") userId: Int): List<Todo>
}