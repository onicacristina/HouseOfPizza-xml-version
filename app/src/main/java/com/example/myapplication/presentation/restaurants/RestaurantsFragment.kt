package com.example.myapplication.presentation.restaurants

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRestaurantsBinding
import com.example.myapplication.domain.model.Restaurant
import com.example.myapplication.presentation.base.BaseViewBindingFragment
import com.example.myapplication.presentation.restaurants.adapter.RestaurantAdapter
import com.example.myapplication.presentation.restaurants.adapter.RestaurantDiffUtil
import com.example.myapplication.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantsFragment :
    BaseViewBindingFragment<FragmentRestaurantsBinding>(R.layout.fragment_restaurants) {

    override val viewBinding: FragmentRestaurantsBinding by viewBinding(FragmentRestaurantsBinding::bind)
    override val viewModel: RestaurantsViewModel by viewModels()
    private lateinit var adapter: RestaurantAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRestaurants()
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
        val recyclerView = viewBinding.rvRestaurants
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = RestaurantAdapter(
            RestaurantDiffUtil(),
            onClickListener = {
            }
        )
        recyclerView.adapter = adapter
    }

    private fun setList(data: List<Restaurant>) {
        adapter.submitList(data)
    }

    private fun renderState(state: RestaurantsViewModel.State) {
        fun renderEmptyState() {
            viewBinding.rvRestaurants.isVisible = false
            viewBinding.tvNoData.isVisible = true
            viewBinding.pbLoading.isVisible = false
            setList(emptyList()) // Clear the list when in empty state
        }

        fun renderListState(data: List<Restaurant>) {
            viewBinding.rvRestaurants.isVisible = true
            viewBinding.tvNoData.isVisible = false
            viewBinding.pbLoading.isVisible = false
            setList(data)
        }

        fun renderLoadingState() {
            viewBinding.rvRestaurants.isVisible = false
            viewBinding.tvNoData.isVisible = false
            viewBinding.pbLoading.isVisible = true
            setList(emptyList()) // Clear the list when in loading state
        }

        when (state) {
            is RestaurantsViewModel.State.Value -> renderListState(state.restaurants)
            is RestaurantsViewModel.State.Loading -> renderLoadingState()
            else -> renderEmptyState()
        }
    }
}