package com.mukesh.machinetask.presentation.views.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mukesh.machinetask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    /**
     * On Create View
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}