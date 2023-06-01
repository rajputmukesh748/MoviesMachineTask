package com.mukesh.machinetask.presentation.views.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mukesh.machinetask.common.mainThread
import com.mukesh.machinetask.common.navigateDirection
import com.mukesh.machinetask.common.readJsonFile
import com.mukesh.machinetask.databinding.SplashBinding
import com.mukesh.machinetask.presentation.viewModel.splash.SplashVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Splash : Fragment() {

    private var _binding: SplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SplashVM>()


    /**
     * On Create View
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SplashBinding.inflate(inflater, container, false)
        return binding.root
    }


    /**
     * On View Created
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSplashTime()
        observeUpdateDb()
    }


    /**
     * Handle Splash Time
     * */
    private fun handleSplashTime() {
        mainThread {
            delay(3000)
            requireContext().readJsonFile(fileName = "appData/response.json"){
                Log.e("ErrorMessage","is ---> $this")
                viewModel.updateDb(this)
            }
        }
    }


    /**
     * Observe Update DB
     * */
    private fun observeUpdateDb() = lifecycleScope.launch {
        viewModel.updateDb.collectLatest {
            requireView().navigateDirection(SplashDirections.actionSplashToHome())
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