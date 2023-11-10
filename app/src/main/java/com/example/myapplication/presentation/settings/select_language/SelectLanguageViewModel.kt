package com.example.myapplication.presentation.settings.select_language

import androidx.lifecycle.ViewModel
import com.example.myapplication.utils.LocaleHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SelectLanguageViewModel : ViewModel() {
    private val _languageObservable: MutableStateFlow<List<Language>> =
        MutableStateFlow(mutableListOf())
    val languageObservable: Flow<List<Language>>
        get() = _languageObservable

    var languageSelected: ELanguage = LocaleHelper.getLocale()


    init {
        getDummyData()
    }

    private fun getDummyData() {
        _languageObservable.value = listOf(
            Language(
                1L,
                ELanguage.ENGLISH,
                isSelected = ELanguage.ENGLISH == languageSelected
            ),
            Language(
                2L,
                ELanguage.ROMANIAN,
                isSelected = ELanguage.ROMANIAN == languageSelected
            ),
        )
    }

    fun selectLanguage(data: Language) {
        val oldData = _languageObservable.value
        val newData = oldData.map { value ->
            val isSelected = value.id == data.id
            value.copy(isSelected = isSelected)
        }
        _languageObservable.value = newData
        languageSelected = data.language
    }
}