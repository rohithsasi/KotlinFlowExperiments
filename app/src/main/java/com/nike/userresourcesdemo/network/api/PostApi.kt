package com.nike.userresourcesdemo.network.api

import com.nike.userresourcesdemo.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/posts")
    suspend fun getAllPosts(): List<Post>

    @GET("/posts")
    suspend fun getPostByUserId(
        @Query("userId") userId: Int): List<Post>
}