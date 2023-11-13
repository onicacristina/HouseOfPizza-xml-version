package com.example.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuRestaurant(
    @PrimaryKey val id: Int,
    val category: String,
    val name: String,
    val topping: List<String>? = null,
    val price: Int,
    val rank: Int? = null
) {
    fun getFormattedToppings() : String? {
        return topping?.joinToString(separator = ", ")
    }
}