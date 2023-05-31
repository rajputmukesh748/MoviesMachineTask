package com.mukesh.machinetask.common


/**
 * Handle Exceptions
 * We don't need to add try/catch everytime
 * */
fun safeCall(callback: () -> Unit) = try {
    callback()
} catch (e:Exception){
    e.printStackTrace()
}