package com.example.myapplication.domain.model

data class Restaurant(
    val id: Int,
    val name: String,
    val address1: String,
    val address2: String,
    val latitude: Double,
    val longitude: Double
)