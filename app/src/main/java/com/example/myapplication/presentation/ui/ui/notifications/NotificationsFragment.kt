package com.example.myapplication.presentation.ui.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.utils.extensions.viewBinding

class NotificationsFragment :
    BaseViewBindingFragment<FragmentNotificationsBinding>(R.layout.fragment_notifications) {

    override val viewBinding: FragmentNotificationsBinding by viewBinding(
        FragmentNotificationsBinding::bind
    )
    override val viewModel: NotificationsViewModel by viewModels()

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