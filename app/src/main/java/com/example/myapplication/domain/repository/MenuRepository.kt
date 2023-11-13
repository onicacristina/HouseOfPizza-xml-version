package com.example.myapplication.domain.repository

import com.example.myapplication.data.dto.MenuDto

interface MenuRepository {
    suspend fun getMenuByRestaurant(restaurantId: Int): MenuDto
}