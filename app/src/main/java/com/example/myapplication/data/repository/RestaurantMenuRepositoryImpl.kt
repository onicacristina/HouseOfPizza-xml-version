package com.example.myapplication.data.repository

import com.example.myapplication.data.dto.RestaurantMenuDto
import com.example.myapplication.data.remote.RestaurantMenuApiService
import com.example.myapplication.domain.repository.RestaurantMenuRepository
import javax.inject.Inject

class RestaurantMenuRepositoryImpl @Inject constructor(
    private val menuApi: RestaurantMenuApiService,
//    private val menuDao: MenuDao
) : RestaurantMenuRepository {
    override suspend fun getMenuByRestaurant(restaurantId: Int):List<RestaurantMenuDto> {
        return menuApi.getMenuByRestaurant(restaurantId = restaurantId)
    }
}