package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.settings.EAppTheme
import com.example.myapplication.utils.Preferences
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
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        setAppMode()
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

    fun setAppMode(){
        when(getAppThemeFromPrefs()){
            EAppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            EAppTheme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun getAppThemeFromPrefs(): EAppTheme{
        return Preferences.getAppTheme()
    }

}