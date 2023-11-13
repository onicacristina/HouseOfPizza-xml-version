package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.MenuDto
import com.example.myapplication.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MenuApiService {
    @GET("restaurants/{restaurantId}")
    suspend fun getMenuByRestaurant(
        @Path("restaurantId") restaurantId: Int,
        @Query("category") category: String = Constants.MENU_CATEGORY,
        @Query("orderBy") orderBy: String = Constants.DEFAULT_ORDER_BY
    ): MenuDto
}