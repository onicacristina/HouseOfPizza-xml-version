package com.example.myapplication.presentation.settings.select_language

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSelectLanguageBinding
import com.example.myapplication.presentation.base.NoBottomNavigationFragment
import com.example.myapplication.utils.extensions.viewBinding

class SelectLanguageFragment :
    NoBottomNavigationFragment<FragmentSelectLanguageBinding>(R.layout.fragment_select_language) {

    override val viewBinding: FragmentSelectLanguageBinding by viewBinding(
        FragmentSelectLanguageBinding::bind
    )
    override val viewModel: SelectLanguageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        initToolbar()
    }

    private fun initToolbar() {
        viewBinding.toolbar.ivBack.visibility = View.VISIBLE
        viewBinding.toolbar.tvTitle.setText(R.string.select_language)
    }

    private fun initListeners() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            navController.popBackStack()
        }
    }
}