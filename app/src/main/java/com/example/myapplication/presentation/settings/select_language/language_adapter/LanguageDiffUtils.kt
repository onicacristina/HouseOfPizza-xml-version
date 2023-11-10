package com.example.myapplication.presentation.settings.select_language.language_adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.presentation.settings.select_language.Language

object LanguageDiffUtils : DiffUtil.ItemCallback<Language>() {
    override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem.isSelected == newItem.isSelected
    }

}