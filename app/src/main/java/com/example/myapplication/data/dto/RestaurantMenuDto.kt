package com.example.myapplication.data.dto

import com.example.myapplication.R
import com.example.myapplication.domain.model.MenuRestaurant

data class RestaurantMenuDto(
    val id: Int,
    val category: String,
    val name: String,
    val topping: List<String>? = null,
    val price: Int,
    val rank: Int? = null
) {
    fun toRestaurantMenu(): MenuRestaurant {
        return MenuRestaurant(
            id = id,
            name = name,
            category = category,
            toppings = topping,
            price = price,
            rank = rank,
            imageResId = getResourceIdByCategory(),
            quantity = 0
        )
    }

    private fun getResourceIdByCategory(): Int {
        return when (name) {
            "Vesuvius" -> R.drawable.vesuvio_pizza
            "Hawaii" -> R.drawable.hawaiian_pizza
            "Parma" -> R.drawable.pizza_parma
            "Coca-cola, 33cl" -> R.drawable.coca_cola
            "Loka citron, 33cl" -> R.drawable.loka_citron
            "Pizzasallad" -> R.drawable.pizzasallad
            "Bröd och smör" -> R.drawable.brod_och_smor
            else -> R.drawable.vesuvio_pizza
        }
    }
}
