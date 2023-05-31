package com.mukesh.machinetask.common.singleClickHandler

import android.view.View

/**
 * @author Mukesh Kumar Rajput
 *
 * On Single Click Listener
 * */
interface OnSingleClickListener {
    fun onClick(view: View?)
}

/**
 * @author Mukesh Kumar Rajput
 *
 * Set On Single Click Listener
 * */
fun View.setOnSingleClickListener(onSingleClickListener: OnSingleClickListener) {
    SingleClickListenerHandler(view = this, onSingleClickListener = onSingleClickListener)
}
