package com.example.myapplication.domain.repository

import com.example.myapplication.data.dto.RestaurantDto
import com.example.myapplication.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    suspend fun getRemoteRestaurantList(): List<RestaurantDto>
    suspend fun getRemoteRestaurantById(id: Int): RestaurantDto
    suspend fun getLocalRestaurantList(): Flow<List<Restaurant>>
    suspend fun getLocalRestaurantById(id: Int): Flow<Restaurant>
    suspend fun saveLocalRestaurantsList(restaurants: List<Restaurant>)
}