package com.nike.userresourcesdemo.network

import com.nike.userresourcesdemo.BuildConfig
import com.nike.userresourcesdemo.network.api.AlbumApi
import com.nike.userresourcesdemo.network.api.PostApi
import com.nike.userresourcesdemo.network.api.TodoApi
import com.nike.userresourcesdemo.network.api.UserApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

object RestClient {

    val userApi: UserApi by lazy { createApiImpl<UserApi>() }

    val postApi: PostApi by lazy { createApiImpl<PostApi>() }

    val albumApi: AlbumApi by lazy { createApiImpl<AlbumApi>() }

    val todoApi: TodoApi by lazy { createApiImpl<TodoApi>() }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        })
        .build()

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    private inline fun <reified T> createApiImpl(
        baseUrl: String = BuildConfig.BASE_API_URL
    ): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(T::class.java)
}