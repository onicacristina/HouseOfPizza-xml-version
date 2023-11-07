package com.example.myapplication.presentation.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class NoBottomNavigationFragment<T : ViewBinding>(@LayoutRes contentLayoutId: Int) : BaseViewBindingFragment<ViewBinding>(contentLayoutId) {

    override var navigationVisibility = View.GONE
}