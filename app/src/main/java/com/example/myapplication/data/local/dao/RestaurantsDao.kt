package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurant")
    fun getAllRestaurants(): Flow<List<Restaurant>>

    @Query("SELECT * FROM restaurant WHERE id = :id")
    fun getRestaurantById(id: Int): Flow<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceRestaurant(restaurant: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Delete
    suspend fun deleteRestaurant(restaurant: Restaurant)

}