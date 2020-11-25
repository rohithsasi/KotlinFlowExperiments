package com.nike.userresourcesdemo.network.api

import com.nike.userresourcesdemo.model.Album
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {

    @GET("/albums")
    suspend fun getAllAlbums(): List<Album>

    @GET("/albums")
    suspend fun getAlbumsByUserId(
        @Query("userId") userId: Int): List<Album>
}