package com.mukesh.machinetask.presentation.viewModel.viewAll

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.mukesh.machinetask.presentation.adapters.GenericAdapter
import com.mukesh.machinetask.databinding.ItemFilterBinding
import com.mukesh.machinetask.databinding.ItemViewAllBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewAllVM @Inject constructor(): ViewModel() {


    /**
     * View All Filter Adapter
     * */
    val filterAdapter = object : GenericAdapter<ItemFilterBinding, Boolean>(){
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemFilterBinding.inflate(inflater, parent, false)

        override fun itemViewType(position: Int) = null

        override fun onBindHolder(binding: ItemFilterBinding, dataClass: Boolean) {

        }

    }


    /**
     * View All Item Adapter
     * */
    val viewAllAdapter = object : GenericAdapter<ItemViewAllBinding, Boolean>(){
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemViewAllBinding.inflate(inflater, parent, false)

        override fun itemViewType(position: Int) = null

        override fun onBindHolder(binding: ItemViewAllBinding, dataClass: Boolean) {

        }

    }

}