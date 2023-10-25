package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.PopularItemsBinding

class MostPopularMealsAdapter :
    RecyclerView.Adapter<MostPopularMealsAdapter.PopularMealsViewHolder>() {

    inner class PopularMealsViewHolder(binding: PopularItemsBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealsViewHolder {
        return PopularMealsViewHolder(
            PopularItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PopularMealsViewHolder, position: Int) {

    }
}