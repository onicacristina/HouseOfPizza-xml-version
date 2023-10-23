package com.example.myapplication.data.dto

data class RestaurantDto(
    val id: Int,
    val name: String,
    val address1: String,
    val address2: String,
    val latitude: Double,
    val longitude: Double
)