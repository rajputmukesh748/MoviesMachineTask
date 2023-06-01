package com.mukesh.machinetask.common

import android.content.Context
import com.mukesh.machinetask.data.remote.ResponseDto


/**
 * Read Json File Data
 * */
fun Context.readJsonFile(fileName: String, callback: ResponseDto.() -> Unit) = safeCall {
    assets.open(fileName).bufferedReader().use {  data ->
        val response = data.readText()
        callback(response.convertStringIntoClass())
    }
}