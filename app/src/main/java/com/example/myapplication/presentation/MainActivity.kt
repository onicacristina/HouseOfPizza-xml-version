package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!navController.popBackStack()) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() {
        val graph = navController.navInflater.inflate(R.navigation.mobile_navigation)
        navController.setGraph(graph, null)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        binding.navView.setupWithNavController(navController)
    }

    fun bottomNavigationVisibility(visibility: Int) {
        binding.navView.visibility = visibility
    }
}