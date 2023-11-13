package com.example.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuRestaurant(
    @PrimaryKey val id: Int,
    val category: String,
    val name: String,
    val toppings: List<String>? = null,
    val price: Int,
    val rank: Int? = null,
    val imageResId: Int
) {
    fun getFormattedToppings(): String? {
        return toppings?.joinToString(separator = ", ")
    }
}