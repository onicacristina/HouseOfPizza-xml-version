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

class RestaurantMenuAdapter(
    diffutils: RestaurantMenuDiffUtil,
    private val onAdditionClickListener: OnAdditionClickListener,
    private val onDecreaseClickListener: OnDecreaseClickListener
) : ListAdapter<MenuRestaurant, RestaurantMenuAdapter.ViewHolder>(diffutils) {

    companion object {
        const val QUANTITY_CHANGED = "QUANTITY_CHANGED"
    }

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
            bindQuantity(data)
            bindAdditionClick(data)
            bindDecreaseClick(data)
        }

        private fun bindIcon(data: MenuRestaurant) {
            binding.ivRestaurant.setImageResource(data.imageResId)
        }

        private fun bindName(data: MenuRestaurant) {
            binding.tvName.text = data.name
        }

        private fun bindCategory(data: MenuRestaurant) {
            binding.tvCategory.text = data.category
        }

        private fun bindPrice(data: MenuRestaurant) {
            val context = binding.tvPrice.context
            binding.tvPrice.text = context.getString(R.string.money, data.price.toString())
        }

        private fun bindToppings(data: MenuRestaurant) {
            val doesHaveToppings = data.toppings.isNullOrEmpty()
            binding.tvToppings.isVisible = !doesHaveToppings
            binding.tvToppingsLabel.isVisible = !doesHaveToppings

            if (!doesHaveToppings) {
                binding.tvToppings.text = data.getFormattedToppings()
            }
        }

        fun bindQuantity(data: MenuRestaurant) {
            binding.tvQuantity.text = data.quantity.toString()
        }

        private fun bindAdditionClick(data: MenuRestaurant) {
            binding.btnAddition.setOnClickListener {
                onAdditionClickListener.invoke(data)
            }
        }

        private fun bindDecreaseClick(data: MenuRestaurant) {
            binding.btnDecrease.setOnClickListener {
                onDecreaseClickListener.invoke(data)
            }
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

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains(QUANTITY_CHANGED))
            holder.bindQuantity(getItem(position))
        else
            holder.bind(getItem(position))
    }
}