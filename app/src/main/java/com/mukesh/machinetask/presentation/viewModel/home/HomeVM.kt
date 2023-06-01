package com.mukesh.machinetask.presentation.viewModel.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mukesh.machinetask.common.ioThread
import com.mukesh.machinetask.common.navigateDirection
import com.mukesh.machinetask.common.safeCall
import com.mukesh.machinetask.common.singleClickHandler.setOnSingleClickListener
import com.mukesh.machinetask.presentation.adapters.GenericAdapter
import com.mukesh.machinetask.presentation.views.home.HomeDirections
import com.mukesh.machinetask.databinding.ItemHomeBinding
import com.mukesh.machinetask.databinding.ItemHomeOwnedBinding
import com.mukesh.machinetask.databinding.ItemHomeRecentBinding
import com.mukesh.machinetask.db.MachineTaskDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val machineTaskDb: MachineTaskDb
) : ViewModel() {


    /**
     * Category Adapter
     * */
    val categoryAdapter = object : GenericAdapter<ItemHomeBinding, Boolean>(){
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemHomeBinding.inflate(inflater, parent, false)

        override fun itemViewType(position: Int) = null

        override fun onBindHolder(binding: ItemHomeBinding, dataClass: Boolean) {
            binding.rvCourse.categoryCourses(dataClass)

            binding.tvViewAll.setOnSingleClickListener {
                navigateDirection(HomeDirections.actionHome2ToViewAll())
            }
        }

    }



    /**
     * Category Courses
     * */
    fun RecyclerView.categoryCourses(isRecentItem: Boolean = true) = safeCall {
        val coursesAdapter = object : GenericAdapter<ViewBinding, Boolean>(){
            override fun onCreateView(
                inflater: LayoutInflater,
                parent: ViewGroup,
                viewType: Int
            ) = if(isRecentItem) ItemHomeRecentBinding.inflate(inflater, parent, false)
            else ItemHomeOwnedBinding.inflate(inflater, parent, false)

            override fun itemViewType(position: Int) = if (position == 0) 1 else 2

            override fun onBindHolder(binding: ViewBinding, dataClass: Boolean) {

            }

        }
        adapter = coursesAdapter.apply {
            submitList(listOf(false, false, false, false))
        }
    }


    init {
        getTasks()
    }


    fun getTasks() = viewModelScope.launch {
        ioThread {
            machineTaskDb.smartDao().getAllSmart()?.let {
                Log.e("dfdsfsd","fsdfsdfsd --->  $it")
            }
        }
    }

}