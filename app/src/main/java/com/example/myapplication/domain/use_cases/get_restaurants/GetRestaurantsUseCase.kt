package com.example.myapplication.domain.use_cases.get_restaurants

import com.example.myapplication.data.dto.toRestaurant
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.repository.RestaurantRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<Resource<List<Restaurant>>> = flow {
        try {
            emit(Resource.Loading())
            val restaurants = repository.getRestaurantList().map { it.toRestaurant() }
            emit(Resource.Success(restaurants))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}