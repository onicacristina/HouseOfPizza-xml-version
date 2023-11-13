package com.example.myapplication.presentation.restaurants.menu

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRestaurantMenuBinding
import com.example.myapplication.domain.model.MenuRestaurant
import com.example.myapplication.presentation.base.NoBottomNavigationFragment
import com.example.myapplication.presentation.restaurants.menu.adapter.RestaurantMenuAdapter
import com.example.myapplication.presentation.restaurants.menu.adapter.RestaurantMenuDiffUtil
import com.example.myapplication.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantMenuFragment :
    NoBottomNavigationFragment<FragmentRestaurantMenuBinding>(R.layout.fragment_restaurant_menu) {
    override val viewBinding: FragmentRestaurantMenuBinding by viewBinding(
        FragmentRestaurantMenuBinding::bind
    )
    override val viewModel: RestaurantMenuViewModel by viewModels()
    private lateinit var adapter: RestaurantMenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        initToolbar()
        setupRecyclerView()
    }

    private fun initToolbar() {
        viewBinding.toolbar.tvTitle.text = getString(R.string.restaurants)
        viewBinding.toolbar.ivBack.isVisible = false
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.state.collect { value ->
                        renderState(value)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.rvRestaurantMenu
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = RestaurantMenuAdapter(
            RestaurantMenuDiffUtil(),
            onAdditionClickListener = { data ->
            },
            onDecreaseClickListener = { data ->
            }
        )
        recyclerView.adapter = adapter
    }

    private fun setList(data: List<MenuRestaurant>) {
        adapter.submitList(data)
    }

    private fun renderState(state: RestaurantMenuViewModel.State) {
        fun renderEmptyState() {
            viewBinding.rvRestaurantMenu.isVisible = false
            viewBinding.tvNoData.isVisible = true
            viewBinding.pbLoading.isVisible = false
            viewBinding.tvNoData.text = getString(R.string.no_restaurants_found)
            setList(emptyList()) // Clear the list when in empty state
        }

        fun renderErrorState(error: String) {
            viewBinding.rvRestaurantMenu.isVisible = false
            viewBinding.tvNoData.isVisible = true
            viewBinding.pbLoading.isVisible = false
            viewBinding.tvNoData.text = error.ifEmpty { getString(R.string.something_went_wrong) }
            setList(emptyList()) // Clear the list when in empty state
        }

        fun renderListState(data: List<MenuRestaurant>) {
            viewBinding.rvRestaurantMenu.isVisible = true
            viewBinding.tvNoData.isVisible = false
            viewBinding.pbLoading.isVisible = false
            setList(data)
        }

        fun renderLoadingState() {
            viewBinding.rvRestaurantMenu.isVisible = false
            viewBinding.tvNoData.isVisible = false
            viewBinding.pbLoading.isVisible = true
            setList(emptyList()) // Clear the list when in loading state
        }

        when (state) {
            is RestaurantMenuViewModel.State.Value -> renderListState(state.restaurantMenu)
            is RestaurantMenuViewModel.State.Loading -> renderLoadingState()
            is RestaurantMenuViewModel.State.Error -> renderErrorState(state.errorMessage)
            else -> renderEmptyState()
        }
    }

}