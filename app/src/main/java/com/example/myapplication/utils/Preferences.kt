package com.example.myapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.settings.EAppTheme

private const val APP_THEME = "app_theme"

object Preferences : BaseFragment() {

    private val sharedPrefs: SharedPreferences?
        get() = getMainActivity()?.getPreferences(Context.MODE_PRIVATE)

    fun setAppTheme(appTheme: EAppTheme) {
        with(sharedPrefs?.edit()) {
            this?.putString(APP_THEME, appTheme.name)
            this?.apply()
        }
    }

    fun getAppTheme(): EAppTheme {
        val defaultValue = EAppTheme.LIGHT
        val savedTheme = sharedPrefs?.getString(APP_THEME, defaultValue.name)
        return EAppTheme.valueOf(savedTheme ?: defaultValue.name)
    }
}
