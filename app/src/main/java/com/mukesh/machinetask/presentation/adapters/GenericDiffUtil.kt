package com.mukesh.machinetask.presentation.adapters

import androidx.recyclerview.widget.DiffUtil


/** You pass <M> data class type
 * and its received in <T> data
 * class type.
 * */
class GenericDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    /** Check old item and new item are same
     *  If they are both same */
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = false
}
