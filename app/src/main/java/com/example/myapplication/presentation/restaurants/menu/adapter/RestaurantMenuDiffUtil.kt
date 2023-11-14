package com.example.myapplication.presentation.restaurants.menu.adapter

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.MenuRestaurant

class RestaurantMenuDiffUtil : DiffUtil.ItemCallback<MenuRestaurant>() {
    override fun areItemsTheSame(oldItem: MenuRestaurant, newItem: MenuRestaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MenuRestaurant, newItem: MenuRestaurant): Boolean {
        return oldItem.name == newItem.name && oldItem.quantity == newItem.quantity
    }

    override fun getChangePayload(oldItem: MenuRestaurant, newItem: MenuRestaurant): Any? {
        val quantityChanged = oldItem.quantity == newItem.quantity
        return Bundle().apply {
            if (quantityChanged)
                putBoolean(RestaurantMenuAdapter.QUANTITY_CHANGED, true)
        }
    }
}