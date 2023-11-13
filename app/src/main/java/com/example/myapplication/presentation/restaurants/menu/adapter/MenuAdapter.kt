package com.example.myapplication.presentation.restaurants.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemMenuRestaurantBinding
import com.example.myapplication.domain.model.MenuRestaurant

typealias OnAdditionClickListener = (MenuRestaurant) -> Unit
typealias OnDecreaseClickListener = (MenuRestaurant) -> Unit

class MenuAdapter(
    diffutils: MenuDiffUtil,
    private val onAdditionClickListener: OnAdditionClickListener,
    private val onDecreaseClickListener: OnDecreaseClickListener
) : ListAdapter<MenuRestaurant, MenuAdapter.ViewHolder>(diffutils) {


    class ViewHolder(
        private val binding: ItemMenuRestaurantBinding,
        private val onAdditionClickListener: OnAdditionClickListener,
        private val onDecreaseClickListener: OnDecreaseClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MenuRestaurant) {
            bindItem(data)
        }

        private fun bindItem(data: MenuRestaurant) {
            bindIcon(data)
            bindName(data)
            bindCategory(data)
            bindPrice(data)
            bindToppings(data)
            bindAdditionClick(data)
            bindDecreaseClick(data)
        }

        private fun bindIcon(data: MenuRestaurant) {
            val imageResourceId =
                if (data.id == 1) R.drawable.restaurant_1 else R.drawable.restaurant_2
            binding.ivRestaurant.setImageResource(imageResourceId)
        }

        private fun bindName(data: MenuRestaurant) {
            binding.tvName.text = data.name
        }

        private fun bindCategory(data: MenuRestaurant) {
            binding.tvCategory.text = data.category
        }

        private fun bindPrice(data: MenuRestaurant) {
            binding.tvPrice.text = data.price.toString()
        }

        private fun bindToppings(data: MenuRestaurant) {
            val doesHaveToppings = data.topping.isNullOrEmpty()
            binding.tvToppings.isVisible = !doesHaveToppings
            binding.tvToppingsLabel.isVisible = !doesHaveToppings

            if (!doesHaveToppings) {
                binding.tvToppings.text = data.getFormattedToppings()
            }
        }

        private fun bindAdditionClick(data: MenuRestaurant) {

        }

        private fun bindDecreaseClick(data: MenuRestaurant) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemMenuRestaurantBinding.inflate(inflater, parent, false),
            onAdditionClickListener,
            onDecreaseClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }
}