package com.example.myapplication.data.repository

import com.example.myapplication.data.dto.MenuDto
import com.example.myapplication.data.remote.MenuApiService
import com.example.myapplication.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuApi: MenuApiService,
//    private val menuDao: MenuDao
) : MenuRepository {
    override suspend fun getMenuByRestaurant(restaurantId: Int): MenuDto {
        return menuApi.getMenuByRestaurant(restaurantId = restaurantId)
    }
}