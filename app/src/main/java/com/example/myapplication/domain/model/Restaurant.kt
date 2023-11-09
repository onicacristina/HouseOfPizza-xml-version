package com.example.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey val id: Int,
    val name: String,
    val address1: String,
    val address2: String,
    val latitude: Double,
    val longitude: Double
)