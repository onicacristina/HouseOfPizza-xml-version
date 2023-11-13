package com.example.myapplication.domain.use_cases.get_menu_by_restaurant

import com.example.myapplication.domain.model.MenuRestaurant
import com.example.myapplication.domain.repository.RestaurantMenuRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMenuByRestaurantIdUseCase @Inject constructor(
    private val repository: RestaurantMenuRepository
) {
    operator fun invoke(restaurantId: Int): Flow<Resource<List<MenuRestaurant>>> = flow {
        try {
            emit(Resource.Loading())
            val menuByRestaurant =
                repository.getMenuByRestaurant(restaurantId).map { it.toRestaurantMenu() }
            emit(Resource.Success(menuByRestaurant))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}