package com.example.myapplication.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.presentation.MainActivity

abstract class BaseFragment : Fragment {

    open var navigationVisibility = View.VISIBLE

    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected open val viewModel: ViewModel? = null

    protected val navController: NavController
        get() = NavHostFragment.findNavController(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initObservers()
    }

    override fun onStart() {
        super.onStart()
        bottomNavigationVisibility()
    }

//    private fun initObservers() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    viewModel?.error?.collect {
//                        showErrorPopup(it)
//                    }
//                }
//            }
//        }
//    }

    private fun bottomNavigationVisibility() {
        getMainActivity()?.bottomNavigationVisibility(navigationVisibility)
    }

    protected fun getMainActivity(): MainActivity? {
        return activity?.let { mainActivity ->
            (mainActivity as MainActivity)
        }
    }

//    protected fun showErrorPopup(errorMessage: String?) {
//        errorMessage?.let { message ->
//            context?.let {
//                showOkDialog(
//                    it,
//                    getString(R.string.something_went_wrong),
//                    message,
//                    getString(R.string.btn_ok)
//                )
//            }
//        }
//    }

}