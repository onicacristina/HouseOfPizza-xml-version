package com.example.myapplication.presentation.restaurants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemRestaurantBinding
import com.example.myapplication.domain.model.Restaurant

typealias OnClickListener = (Restaurant) -> Unit

class RestaurantAdapter(
    diffutils: RestaurantDiffUtil,
    private val onClickListener: OnClickListener
) : ListAdapter<Restaurant, RestaurantAdapter.ViewHolder>(diffutils) {


    class ViewHolder(
        private val binding: ItemRestaurantBinding,
        private val onClickListener: OnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Restaurant) {
            bindItem(data)
        }

        private fun bindItem(data: Restaurant) {
            bindIcon(data)
            bindName(data)
            bindAddress(data)
            bindClick(data)
        }

        private fun bindIcon(data: Restaurant) {
            val imageResourceId =
                if (data.id == 1) R.drawable.restaurant_1 else R.drawable.restaurant_2
            binding.ivRestaurant.setImageResource(imageResourceId)
        }

        private fun bindName(data: Restaurant) {
            binding.tvName.text = data.name
        }

        private fun bindAddress(data: Restaurant) {
            binding.tvAddress.text = data.getFullAddress()
        }

        private fun bindClick(data: Restaurant) {
            binding.cvCard.setOnClickListener {
                onClickListener.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemRestaurantBinding.inflate(inflater, parent, false),
            onClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }
}