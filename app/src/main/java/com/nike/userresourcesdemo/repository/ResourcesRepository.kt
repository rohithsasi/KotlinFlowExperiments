package com.nike.userresourcesdemo.repository

import com.nike.userresourcesdemo.model.Resources
import com.nike.userresourcesdemo.network.RestClient.albumApi
import com.nike.userresourcesdemo.network.RestClient.postApi
import com.nike.userresourcesdemo.network.RestClient.todoApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ResourcesRepository {

    suspend fun getResourcesByUserId(userId: Int): Resources = withContext(IO) {
        val posts = async { postApi.getPostByUserId(userId) }
        val albums = async { albumApi.getAlbumsByUserId(userId) }
        val todos = async { todoApi.getTodosByUserId(userId) }

        Resources(posts.await(), albums.await(), todos.await())
    }

}