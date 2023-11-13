package com.example.myapplication.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.utils.Preferences
import com.example.myapplication.utils.extensions.viewBinding

class SettingsFragment :
    BaseViewBindingFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    override val viewBinding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    override val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initSwitch()
    }

    private fun initViews() {
        initToolbar()
    }

    private fun initToolbar() {
        viewBinding.toolbar.tvTitle.setText(R.string.settings)
    }

    private fun initListeners() {
        viewBinding.languageContainer.setOnClickListener {
            navController.navigate(R.id.select_language_fragment)
        }
        initSwitchListener()
    }
    
    private fun initSwitchListener(){
        viewBinding.btnSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
                Preferences.setAppTheme(EAppTheme.DARK)
            else
                Preferences.setAppTheme(EAppTheme.LIGHT)
            getMainActivity()?.setAppMode()
        }
    }

    private fun initSwitch() {
        val appTheme = Preferences.getAppTheme()
        viewBinding.btnSwitch.isChecked = EAppTheme.LIGHT != appTheme
    }

}