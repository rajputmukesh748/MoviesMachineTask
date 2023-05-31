package com.mukesh.machinetask.common

import android.content.Context


/**
 * Read Json File Data
 * */
fun Context.readJsonFile(fileName: String, callback: String.() -> Unit) = safeCall {
    assets.open(fileName).bufferedReader().use {  data ->
        callback(data.readText())
    }
}