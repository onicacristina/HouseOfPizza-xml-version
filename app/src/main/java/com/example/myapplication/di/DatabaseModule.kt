package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.local.PizzaLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providePizzaLocalDatabase(app: Application): PizzaLocalDatabase {
        return Room.databaseBuilder(
            app,
            PizzaLocalDatabase::class.java,
            PizzaLocalDatabase.DATABASE_NAME
        ).build()
    }
}