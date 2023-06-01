package com.mukesh.machinetask.common

import android.util.Log
import com.mukesh.machinetask.BuildConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.logging.Logger


/**
 * Dispatchers
 * */
val mainDispatcher by lazy { Dispatchers.Main }
val ioDispatcher by lazy { Dispatchers.IO }
val defaultDispatcher by lazy { Dispatchers.Default }
val unconfinedDispatcher by lazy { Dispatchers.Unconfined }


/**
 * Create a main thread
 * */
fun mainThread(coroutineScope: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(coroutineException + Job() + mainDispatcher).launch { coroutineScope() }


/**
 * Create a io thread
 * */
fun ioThread(coroutineScope: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(coroutineException + Job() + ioDispatcher).launch { coroutineScope() }


/**
 *Create a default thread
 *  */
fun defaultThread(coroutineScope: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(coroutineException + Job() + defaultDispatcher).launch { coroutineScope() }


/**
 * Create a unconfined thread
 * */
fun unconfinedThread(coroutineScope: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(coroutineException + Job() + unconfinedDispatcher).launch { coroutineScope() }


/**
 * Coroutine Exception
 * */
val coroutineException = CoroutineExceptionHandler { _, throwable ->
    if (BuildConfig.DEBUG) Log.e("Error Throw", "Error throw in coroutine:- ${throwable.localizedMessage}")
}