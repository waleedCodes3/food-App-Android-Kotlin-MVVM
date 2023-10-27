package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.MealItemBinding
import com.example.foodapp.pojo.MealsList

class FavouritesListAdapter : RecyclerView.Adapter<FavouritesListAdapter.MyViewHolder>() {
        class MyViewHolder(var binding: MealItemBinding) : ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<MealsList.Meal>() {
        override fun areItemsTheSame(oldItem: MealsList.Meal, newItem: MealsList.Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealsList.Meal, newItem: MealsList.Meal): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMeal = differ.currentList[position]
        holder.binding.apply {

            Glide.with(holder.itemView).load(currentMeal.strMealThumb).into(mealImage)
            mealTitle.text = currentMeal.strMeal
        }

    }

}