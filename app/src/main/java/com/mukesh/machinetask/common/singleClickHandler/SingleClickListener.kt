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


fun View.setOnSingleClickListener(callback: View.() -> Unit){
    SingleClickListenerHandler(view = this, onSingleClickListener = object : OnSingleClickListener{
        override fun onClick(view: View?) {
            if (view == this@setOnSingleClickListener) callback.invoke(view)
        }
    })
}
