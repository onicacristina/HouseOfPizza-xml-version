package com.example.myapplication.data.repository

import com.example.myapplication.data.dto.RestaurantDto
import com.example.myapplication.data.local.dao.RestaurantsDao
import com.example.myapplication.data.remote.RestaurantApiService
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApiService,
    private val restaurantsDao: RestaurantsDao
) : RestaurantRepository {
    override suspend fun getRemoteRestaurantList(): List<RestaurantDto> {
        return restaurantApi.getRestaurantList()
    }

    override suspend fun getRemoteRestaurantById(id: Int): RestaurantDto {
        return restaurantApi.getRestaurantById(id = id)
    }

    override suspend fun getLocalRestaurantList(): Flow<List<Restaurant>> {
        return restaurantsDao.getAllRestaurants()
    }

    override suspend fun getLocalRestaurantById(id: Int): Flow<Restaurant> {
        return restaurantsDao.getRestaurantById(id)
    }

    override suspend fun saveLocalRestaurantsList(restaurants: List<Restaurant>) {
        restaurantsDao.insertRestaurants(restaurants)
    }

}