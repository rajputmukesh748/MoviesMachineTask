package com.mukesh.machinetask.presentation.viewModel.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mukesh.machinetask.BuildConfig
import com.mukesh.machinetask.common.loadImage
import com.mukesh.machinetask.common.navigateDirection
import com.mukesh.machinetask.common.safeCall
import com.mukesh.machinetask.common.singleClickHandler.setOnSingleClickListener
import com.mukesh.machinetask.data.local.CoursesDto
import com.mukesh.machinetask.data.local.SmartDto
import com.mukesh.machinetask.presentation.adapters.GenericAdapter
import com.mukesh.machinetask.presentation.views.home.HomeDirections
import com.mukesh.machinetask.databinding.ItemHomeBinding
import com.mukesh.machinetask.databinding.ItemHomeOwnedBinding
import com.mukesh.machinetask.databinding.ItemHomeRecentBinding
import com.mukesh.machinetask.domain.usecase.GetCoursesWithIdsUseCase
import com.mukesh.machinetask.domain.usecase.getAllCategories.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getCoursesWithIdsUseCase: GetCoursesWithIdsUseCase
) : ViewModel() {

    val allCategoriesList by lazy { ArrayList<SmartDto>() }


    /**
     * Category Adapter
     * */
    val categoryAdapter = object : GenericAdapter<ItemHomeBinding, SmartDto>(){
        override fun onCreateView(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ) = ItemHomeBinding.inflate(inflater, parent, false)

        override fun itemViewType(position: Int) = null

        override fun onBindHolder(binding: ItemHomeBinding, dataClass: SmartDto) {
            binding.tvHeading.text = dataClass.label.orEmpty()
            binding.rvCourse.categoryCourses(dataClass.id != "owned", dataClass.coursesList ?: emptyList())

            binding.tvViewAll.setOnSingleClickListener {
                navigateDirection(HomeDirections.actionHome2ToViewAll())
            }
        }

    }



    /**
     * Category Courses
     * */
    fun RecyclerView.categoryCourses(isRecentItem: Boolean = true, list: List<CoursesDto>) = safeCall {
        val coursesAdapter = object : GenericAdapter<ViewBinding, CoursesDto>(){
            override fun onCreateView(
                inflater: LayoutInflater,
                parent: ViewGroup,
                viewType: Int
            ) = if(isRecentItem) ItemHomeRecentBinding.inflate(inflater, parent, false)
            else ItemHomeOwnedBinding.inflate(inflater, parent, false)

            override fun itemViewType(position: Int) = if (position == 0) 1 else 2

            override fun onBindHolder(binding: ViewBinding, dataClass: CoursesDto) {
                if(binding is ItemHomeRecentBinding){
                    binding.bind(dataClass = dataClass)
                } else if(binding is ItemHomeOwnedBinding){
                    binding.bind(dataClass = dataClass)
                }
            }

        }
        adapter = coursesAdapter.apply {
            submitList(list)
        }
    }



    /**
     * Set Up Adapter Recent Binding
     * */
    private fun ItemHomeRecentBinding.bind(dataClass: CoursesDto) = safeCall {
        ivTitle.text = dataClass.title.orEmpty().ifEmpty { "N/A" }
        ivAuthor.text = dataClass.educator.orEmpty().ifEmpty { "N/A" }
        ivBanner.loadImage(url = { BuildConfig.IMAGE_BASE_URL.plus(dataClass.id) })
    }


    /**
     * Set Up Adapter Owned Binding
     * */
    private fun ItemHomeOwnedBinding.bind(dataClass: CoursesDto) = safeCall {
        ivTitle.text = dataClass.title.orEmpty().ifEmpty { "N/A" }
        ivAuthor.text = dataClass.educator.orEmpty().ifEmpty { "N/A" }
        ivBanner.loadImage(url = { BuildConfig.IMAGE_BASE_URL.plus(dataClass.id) })
    }


    /**
     * Get All Categories
     * */
    private val _categoriesData by lazy { MutableSharedFlow<List<SmartDto>?>() }
    val categoriesData get() = _categoriesData.asSharedFlow()
    fun getAllCategories() = getAllCategoriesUseCase().onEach { result ->
        _categoriesData.emit(result)
    }.launchIn(viewModelScope)



    /**
     * Get All Courses With ID's
     * */
    private val _coursesList by lazy { MutableSharedFlow<List<CoursesDto>?>() }
    val coursesList get() = _coursesList.asSharedFlow()
    fun getCourses(list: List<Int>) = getCoursesWithIdsUseCase(list.map { it.toString() }).onEach { result ->
        _coursesList.emit(result)
    }.launchIn(viewModelScope)

}