package com.example.myapplication.data.repository

import com.example.myapplication.data.dto.RestaurantDto
import com.example.myapplication.data.remote.RestaurantApiService
import com.example.myapplication.domain.repository.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApiService
) : RestaurantRepository {
    override suspend fun getRestaurantList(): List<RestaurantDto> {
        return restaurantApi.getRestaurantList()
    }

    override suspend fun getRestaurantById(id: Int): RestaurantDto {
        return restaurantApi.getRestaurantById(id = id)
    }

}