package com.mukesh.machinetask.common

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.google.gson.Gson
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import java.io.File


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


/**
 * Load Image
 * */
fun ImageView.loadImage(
    url: () -> String, errorPlaceHolder: () -> Int = { DRAWABLE_ALIAS.ic_broken_image }
) = try {
    val circularProgressDrawable = CircularProgressDrawable(this.context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
    load(if (url().startsWith("http")) url().plus("/169_820.jpg") else File(url())) {
        placeholder(circularProgressDrawable)
        crossfade(true)
        error(errorPlaceHolder())
    }
} catch (e: Exception) {
    e.printStackTrace()
}
