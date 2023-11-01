package com.example.myapplication.data.dto

import com.example.myapplication.domain.model.Restaurant

data class RestaurantDto(
    val id: Int,
    val name: String,
    val address1: String,
    val address2: String,
    val latitude: Double,
    val longitude: Double
)

fun RestaurantDto.toRestaurant(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        address1 = address1,
        address2 = address2,
        latitude = latitude,
        longitude = longitude
    )
}