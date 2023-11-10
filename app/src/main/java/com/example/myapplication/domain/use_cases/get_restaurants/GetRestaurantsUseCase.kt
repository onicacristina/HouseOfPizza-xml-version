package com.example.myapplication.domain.use_cases.get_restaurants

import android.util.Log
import com.example.myapplication.data.dto.toRestaurant
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.repository.RestaurantRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<Resource<List<Restaurant>>> = flow {
        try {
            emit(Resource.Loading())
            val restaurants = repository.getRemoteRestaurantList().map { it.toRestaurant() }
            repository.saveLocalRestaurantsList(restaurants)
            emit(Resource.Success(restaurants))
        } catch (e: HttpException) {
            handleApiError(e)
        } catch (e: IOException) {
            handleIoError()
        }
    }.onEach {
        Log.d("GetRestaurantsUseCase", "Result: $it")
    }.flowOn(Dispatchers.IO)

    private suspend fun FlowCollector<Resource<List<Restaurant>>>.handleApiError(e: HttpException) {
        repository.getLocalRestaurantList().collect { localRestaurants ->
            if (localRestaurants.isNotEmpty()) {
                emit(Resource.Success(localRestaurants))
            } else {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
            }
        }
    }

    private suspend fun FlowCollector<Resource<List<Restaurant>>>.handleIoError() {
        repository.getLocalRestaurantList().collect { localRestaurants ->
            if (localRestaurants.isNotEmpty()) {
                emit(Resource.Success(localRestaurants))
            } else {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }
}