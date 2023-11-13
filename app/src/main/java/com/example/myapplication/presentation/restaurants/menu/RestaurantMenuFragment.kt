package com.example.myapplication.presentation.restaurants.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRestaurantMenuBinding
import com.example.myapplication.presentation.base.NoBottomNavigationFragment
import com.example.myapplication.utils.extensions.viewBinding

class RestaurantMenuFragment :
    NoBottomNavigationFragment<FragmentRestaurantMenuBinding>(R.layout.fragment_restaurant_menu) {
    override val viewBinding: FragmentRestaurantMenuBinding by viewBinding(
        FragmentRestaurantMenuBinding::bind
    )
    override val viewModel: RestaurantMenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {

    }

    private fun initListeners() {

    }

    private fun initObservers() {

    }

}