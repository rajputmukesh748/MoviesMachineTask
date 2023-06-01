package com.mukesh.machinetask.presentation.views.viewAll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mukesh.machinetask.common.ID_ALIAS
import com.mukesh.machinetask.common.STRING_ALIAS
import com.mukesh.machinetask.common.navigateBack
import com.mukesh.machinetask.common.safeCall
import com.mukesh.machinetask.common.singleClickHandler.OnSingleClickListener
import com.mukesh.machinetask.common.singleClickHandler.setOnSingleClickListener
import com.mukesh.machinetask.presentation.viewModel.viewAll.ViewAllVM
import com.mukesh.machinetask.databinding.ViewAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewAll : Fragment(), OnSingleClickListener {

    private var _binding: ViewAllBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ViewAllVM>()


    /**
     * On Create View
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewAllBinding.inflate(inflater, container, false)
        return binding.root
    }


    /**
     * On View Created
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }


    /**
     * Set Up Ui
     * */
    private fun setUpUI() = safeCall {
        binding.toolBar.tvHeading.text = getString(STRING_ALIAS.recently_visit)
        binding.rvFilters.adapter = viewModel.filterAdapter.apply {
            submitList(listOf(false, false, false, false))
        }
        binding.rvMain.adapter = viewModel.viewAllAdapter.apply {
            submitList(listOf(false, false, false, false))
        }
        clickInitializer()
    }


    /**
     * Click Initializer
     * */
    private fun clickInitializer() = safeCall {
        binding.toolBar.ivBack.setOnSingleClickListener(this)
    }


    /**
     * On Click
     * */
    override fun onClick(view: View?) {
        when(view?.id){
            ID_ALIAS.ivBack -> view.navigateBack()
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