package com.nike.userresourcesdemo.extension

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> CoroutineScope.launch(
    liveData: MutableLiveData<Result<T>>,
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
) = launch(context + liveData.getExceptionHandler()) { liveData.postSuccess(block()) }

fun <T> CoroutineScope.launchAndCollect(
    liveData: MutableLiveData<Result<T>>,
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Flow<T>
) = launch(context + liveData.getExceptionHandler()) { block().collect { liveData.postSuccess(it) } }
