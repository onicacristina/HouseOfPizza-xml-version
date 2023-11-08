package com.example.myapplication.presentation.get_started

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGetStartedBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.presentation.base.NoBottomNavigationFragment
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.utils.extensions.viewBinding

class GetStartedFragment : NoBottomNavigationFragment<FragmentGetStartedBinding>(R.layout.fragment_get_started) {

    override val viewBinding: FragmentGetStartedBinding by viewBinding(FragmentGetStartedBinding::bind)
    override val viewModel: GetStartedViewModel by viewModels()

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