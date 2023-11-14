package com.example.myapplication.presentation.restaurants.menu

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.MenuRestaurant
import com.example.myapplication.domain.use_cases.get_menu_by_restaurant.GetMenuByRestaurantIdUseCase
import com.example.myapplication.utils.DefaultStateDelegate
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.StateDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RestaurantMenuViewModel @Inject constructor(
    private val getMenuByRestaurantId: GetMenuByRestaurantIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(),
    StateDelegate<RestaurantMenuViewModel.State> by DefaultStateDelegate(State.Loading) {

    private val navArgs: RestaurantMenuFragmentArgs by lazy {
        RestaurantMenuFragmentArgs.fromSavedStateHandle(savedStateHandle)
    }

    private val _restaurantsObservable: MutableStateFlow<List<MenuRestaurant>> =
        MutableStateFlow(emptyList())

    init {
        getRestaurantMenu()
    }

    private fun getRestaurantMenu() {
        getMenuByRestaurantId(navArgs.restaurantId).onEach { result ->
            Log.e("result", "result" + result)
            currentState = when (result) {
                is Resource.Success -> {
                    val restaurantMenu = result.data ?: emptyList()
                    _restaurantsObservable.value = restaurantMenu
                    Log.e("result", "result" + restaurantMenu)
                    if (restaurantMenu.isEmpty()) State.Empty else State.Value(restaurantMenu)
                }

                is Resource.Error -> State.Error(result.message ?: "")
                is Resource.Loading -> State.Loading
            }
        }.launchIn(viewModelScope)
    }

    fun onAddition(data: MenuRestaurant) {
        val oldData = _restaurantsObservable.value
        val newData = oldData.map { value ->
            if (value.id == data.id)
                value.copy(quantity = data.quantity + 1)
            else
                value
        }
        currentState = State.Value(restaurantMenu = newData)
    }

    fun onDecrease(data: MenuRestaurant) {
        val oldData = _restaurantsObservable.value
        val newData = oldData.map { value ->
            if (value.id == data.id && data.quantity > 0)
                value.copy(quantity = data.quantity - 1)
            else
                value
        }
        currentState = State.Value(restaurantMenu = newData)
    }

    sealed class State {
        object Loading : State()
        object Empty : State()
        data class Value(val restaurantMenu: List<MenuRestaurant>) : State()
        data class Error(val errorMessage: String) : State()
    }

}