package com.example.myapplication.domain.repository

import com.example.myapplication.data.dto.RestaurantMenuDto

interface RestaurantMenuRepository {
    suspend fun getMenuByRestaurant(restaurantId: Int): List<RestaurantMenuDto>
}