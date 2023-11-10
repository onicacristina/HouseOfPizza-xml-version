package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.RestaurantsDao
import com.example.myapplication.domain.model.Restaurant

@Database(
    entities = [Restaurant::class],
    version = 1
)
abstract class PizzaLocalDatabase : RoomDatabase() {

    abstract val restaurantsDao: RestaurantsDao

    companion object {
        const val DATABASE_NAME = "pizza_db"
    }
}