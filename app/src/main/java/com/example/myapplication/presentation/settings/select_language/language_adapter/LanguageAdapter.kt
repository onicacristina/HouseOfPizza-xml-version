package com.example.myapplication.presentation.settings.select_language.language_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemLanguageBinding
import com.example.myapplication.presentation.settings.select_language.Language

typealias OnItemClickListener = (Language) -> Unit

class LanguageAdapter(
    private val onItemClickListener: OnItemClickListener
) : ListAdapter<Language, LanguageAdapter.ViewHolder>(LanguageDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLanguageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: ItemLanguageBinding,
        private val onItemClickListener: OnItemClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Language) {
            val context = binding.tvText.context
            binding.tvText.text = context.getString(item.language.languageName)

            binding.clLanguage.setOnClickListener {
                onItemClickListener.invoke(item)
            }

            setSelected(item)
        }

        private fun setSelected(data: Language) {
            if (data.isSelected)
                binding.ivArrow.setImageResource(R.drawable.ic_language_checked)
            else
                binding.ivArrow.setImageResource(R.drawable.ic_language_unchecked)
        }
    }
}