package com.example.myapplication.domain.repository

import com.example.myapplication.data.dto.RestaurantDto

interface RestaurantRepository {
    suspend fun getRestaurantList(): List<RestaurantDto>
    suspend fun getRestaurantById(id: Int): RestaurantDto
}