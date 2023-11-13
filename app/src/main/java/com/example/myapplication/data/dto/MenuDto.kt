package com.example.myapplication.data.dto

data class MenuDto(
    val id: Int,
    val category: String,
    val name: String,
    val topping: List<String>? = null,
    val price: Int,
    val rank: Int? = null
)
