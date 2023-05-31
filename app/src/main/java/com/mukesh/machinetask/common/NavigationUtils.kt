package com.mukesh.machinetask.common

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController


/**
 * Navigate between fragments using Direction Classes
 * */
fun View.navigateDirection(direction: NavDirections){
    try {
        findNavController().navigate(direction)
    }catch (e:Exception){
        e.printStackTrace()
    }
}


/**
 * Go to previous fragment
 *
 *  and also need to pop any fragment then pass those ID
 * */
fun View.navigateBack(popStackId: Int? = null){
    try {
        if (popStackId != null)
            findNavController().popBackStack(popStackId, true)
        findNavController().popBackStack()
    }catch (e:Exception){
        e.printStackTrace()
    }
}