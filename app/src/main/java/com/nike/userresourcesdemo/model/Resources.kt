package com.nike.userresourcesdemo.model

data class Resources(
    val posts: List<Post>,
    val albums: List<Album>,
    val todos: List<Todo>
)