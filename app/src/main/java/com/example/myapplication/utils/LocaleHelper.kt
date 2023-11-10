package com.example.myapplication.utils

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.myapplication.presentation.settings.select_language.ELanguage
import java.util.Locale

object LocaleHelper {

    fun setLocale(language: ELanguage) {
        val appLocale = LocaleListCompat.forLanguageTags(language.language)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    fun getLocale(): ELanguage {
        val locales = AppCompatDelegate.getApplicationLocales()
        val selectedLocale = locales[0] ?: Locale.US
        return ELanguage.values()
            .firstOrNull { value -> value.locale.language == selectedLocale.language }
            ?: ELanguage.ENGLISH
    }

}