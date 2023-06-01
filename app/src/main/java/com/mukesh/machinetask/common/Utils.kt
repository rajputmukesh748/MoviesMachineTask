package com.mukesh.machinetask.common

import com.google.gson.Gson
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


/**
 * Handle Exceptions
 * We don't need to add try/catch everytime
 * */
fun safeCall(callback: () -> Unit) = try {
    callback()
} catch (e:Exception){
    e.printStackTrace()
}


/**
 * Convert any data into json data
 * */
fun Any.convertGsonString(): String = Gson().toJson(this)


/**
 * Convert String into Data Class
 * */
inline fun <reified T> String.convertStringIntoClass(): T = Gson().fromJson(this, T::class.java)


/**
 * Check Response State
 * and Handle Accordingly
 * */
//suspend fun <T> SharedFlow<NetworkState<T?>>.collectAsState(
//    onLoading: () -> Unit = {}, onSuccess: T.() -> Unit = {}, onError: String.() -> Unit = {}
//) {
//    this.collectLatest { state ->
//        safeCall {
//            when (state) {
//                is NetworkState.LOADING -> onLoading.invoke()
//                is NetworkState.ERROR -> onError.invoke(state.error.toString())
//                is NetworkState.SUCCESS -> state.data?.let { onSuccess.invoke(it) }
//            }
//        }
//    }
//}
