package com.example.myapplication.presentation.restaurants.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.Restaurant

class RestaurantDiffUtil : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.name == newItem.name
    }
}