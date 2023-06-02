package com.mukesh.machinetask.presentation.views.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mukesh.machinetask.common.STRING_ALIAS
import com.mukesh.machinetask.common.safeCall
import com.mukesh.machinetask.presentation.viewModel.home.HomeVM
import com.mukesh.machinetask.databinding.HomeBinding
import com.mukesh.machinetask.databinding.ViewAllBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Home : Fragment() {

    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeVM>()


    /**
     * On Create View
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    /**
     * On View Created
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        observerCategoryList()
        observeCoursesList()
        viewModel.getAllCategories()
    }


    /**
     * Set Up Ui
     * */
    private fun setUpUI() = safeCall {
        binding.toolBar.ivBack.visibility = View.INVISIBLE
        binding.toolBar.tvHeading.text = getString(STRING_ALIAS.home)
        binding.rvMain.adapter = viewModel.categoryAdapter
    }


    /**
     * Observe Category List
     * */
    private fun observerCategoryList() = lifecycleScope.launch {
        viewModel.categoriesData.collectLatest {
            viewModel.allCategoriesList.clear()
            viewModel.allCategoriesList.addAll(it ?: emptyList())
            viewModel.getCourses(viewModel.allCategoriesList.map { category -> category.courses }.flatMap { courseList -> courseList ?: emptyList() })
        }
    }


    /**
     * Observe Courses List
     * */
    @SuppressLint("NotifyDataSetChanged")
    private fun observeCoursesList() = lifecycleScope.launch {
        viewModel.coursesList.collectLatest {
            it?.map { course ->
                viewModel.allCategoriesList.map { categories ->
                    if (categories.courses?.contains(course.id) == true){
                        categories.coursesList?.add(course)
                    }
                }
            }
            viewModel.categoryAdapter.submitList(viewModel.allCategoriesList)
            viewModel.categoryAdapter.notifyDataSetChanged()
        }
    }


    /**
     * On Destroy View
     * */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}