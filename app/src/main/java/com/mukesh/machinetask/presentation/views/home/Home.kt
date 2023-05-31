package com.mukesh.machinetask.presentation.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mukesh.machinetask.common.STRING_ALIAS
import com.mukesh.machinetask.common.safeCall
import com.mukesh.template.databinding.HomeBinding
import com.mukesh.template.databinding.ViewAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!


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
    }


    /**
     * Set Up Ui
     * */
    private fun setUpUI() = safeCall {
        binding.toolBar.tvHeading.text = getString(STRING_ALIAS.home)
    }


    /**
     * On Destroy View
     * */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}