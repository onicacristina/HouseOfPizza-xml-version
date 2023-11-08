package com.example.myapplication.presentation.restaurants

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRestaurantsBinding
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantsFragment :
    BaseViewBindingFragment<FragmentRestaurantsBinding>(R.layout.fragment_restaurants) {

    override val viewBinding: FragmentRestaurantsBinding by viewBinding(FragmentRestaurantsBinding::bind)
    override val viewModel: RestaurantsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        initToolbar()
    }

    private fun initToolbar() {
    }

    private fun initListeners() {

    }
}