package com.example.myapplication.presentation.settings.select_language

data class Language(
    val id: Long,
    val language: ELanguage,
    val isSelected: Boolean = false
)
