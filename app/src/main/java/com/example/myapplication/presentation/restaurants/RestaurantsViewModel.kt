package com.example.myapplication.presentation.restaurants

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.domain.use_cases.get_restaurants.GetRestaurantsUseCase
import com.example.myapplication.utils.DefaultStateDelegate
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.StateDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
) :
    ViewModel(),
    StateDelegate<RestaurantsViewModel.State> by DefaultStateDelegate(State.Loading) {

    init {
        getRestaurants()
    }

    fun getRestaurants() {
        getRestaurantsUseCase().onEach { result ->
            Log.e("result", "result" + result)
            currentState = when (result) {
                is Resource.Success -> {
                    val restaurants = result.data ?: emptyList()
                    Log.e("result", "result" + restaurants)
                    if (restaurants.isEmpty()) State.Empty else State.Value(restaurants)
                }

                is Resource.Error -> State.Error(result.message ?: "")
                is Resource.Loading -> State.Loading
            }
        }.launchIn(viewModelScope)
    }

    sealed class State {
        object Loading : State()
        object Empty : State()
        data class Value(val petsList: List<Restaurant>) : State()

        data class Error(val errorMessage: String) : State()
    }

}