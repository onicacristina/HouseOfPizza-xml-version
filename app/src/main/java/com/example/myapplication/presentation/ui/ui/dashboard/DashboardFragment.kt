package com.example.myapplication.presentation.ui.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.utils.extensions.viewBinding

class DashboardFragment :
    BaseViewBindingFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {

    override val viewBinding: FragmentDashboardBinding by viewBinding(FragmentDashboardBinding::bind)
    override val viewModel: DashboardViewModel by viewModels()

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