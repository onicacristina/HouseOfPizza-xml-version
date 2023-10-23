package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.RestaurantDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApiService {
    @GET("restaurants/")
    suspend fun getRestaurantList(): List<RestaurantDto>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: Int): RestaurantDto

}