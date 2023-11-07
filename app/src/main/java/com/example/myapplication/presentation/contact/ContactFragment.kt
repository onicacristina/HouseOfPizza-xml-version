package com.example.myapplication.presentation.contact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentContactBinding
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.utils.extensions.viewBinding

class ContactFragment : BaseViewBindingFragment<FragmentContactBinding>(R.layout.fragment_contact) {

    override val viewBinding: FragmentContactBinding by viewBinding(FragmentContactBinding::bind)
    override val viewModel: ContactViewModel by viewModels()

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