package com.example.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.presentation.restaurants.RestaurantsViewModel
import com.example.myapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModel



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: RestaurantsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getRestaurants()
    }
}