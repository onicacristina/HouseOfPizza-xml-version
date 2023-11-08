package com.example.myapplication.presentation.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrdersBinding
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.utils.extensions.viewBinding

class OrdersFragment :
    BaseViewBindingFragment<FragmentOrdersBinding>(R.layout.fragment_orders) {

    override val viewBinding: FragmentOrdersBinding by viewBinding(
        FragmentOrdersBinding::bind
    )
    override val viewModel: OrdersViewModel by viewModels()

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