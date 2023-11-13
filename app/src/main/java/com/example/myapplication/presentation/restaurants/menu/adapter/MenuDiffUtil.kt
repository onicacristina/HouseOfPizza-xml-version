package com.example.myapplication.presentation.restaurants.menu.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.MenuRestaurant

class MenuDiffUtil : DiffUtil.ItemCallback<MenuRestaurant>() {
    override fun areItemsTheSame(oldItem: MenuRestaurant, newItem: MenuRestaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MenuRestaurant, newItem: MenuRestaurant): Boolean {
        return oldItem.name == newItem.name
    }

    override fun getChangePayload(oldItem: MenuRestaurant, newItem: MenuRestaurant): Any? {
        return super.getChangePayload(oldItem, newItem)
        //TODO
    }
}