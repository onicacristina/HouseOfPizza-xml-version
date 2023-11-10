package com.example.myapplication.presentation.settings.select_language

import com.example.myapplication.R
import java.util.Locale

enum class ELanguage(val language: String, val languageName: Int) {
    ROMANIAN("ro", R.string.romanian),
    ENGLISH("en", R.string.english);

    val locale: Locale
        get() = Locale.forLanguageTag(language)
}