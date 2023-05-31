package com.mukesh.machinetask.common.singleClickHandler

import android.os.SystemClock
import android.view.View
import java.util.concurrent.TimeUnit

/**
 *
 * @author Mukesh Kumar Rajput
 *
 * Single Click Listener Handler
 * */
class SingleClickListenerHandler(
    view: View,
    private val onSingleClickListener: OnSingleClickListener
) : View.OnClickListener {

    /**
     * Store Calculate Time
     * */
    private var lastClickTime = 0L


    /**
     * Initializer
     * */
    init {
        view.setOnClickListener(this)
    }


    /**
     * Perform Click
     * */
    override fun onClick(view: View?) {
        if ((SystemClock.elapsedRealtime() - lastClickTime) < TimeUnit.SECONDS.toMillis(1)) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        onSingleClickListener.onClick(view = view)
    }
}