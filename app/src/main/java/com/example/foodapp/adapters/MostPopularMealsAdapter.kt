package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.PopularItemsBinding
import com.example.foodapp.pojo.CategoryList
import com.example.foodapp.pojo.MealsList

class MostPopularMealsAdapter :
    Adapter<MostPopularMealsAdapter.PopularMealsViewHolder>() {
    private var categoryMealsList = ArrayList<CategoryList.CategoryMeal>()

    fun setMeals(mealsList: ArrayList<CategoryList.CategoryMeal>) {

        categoryMealsList = mealsList
        notifyDataSetChanged()
    }

    inner class PopularMealsViewHolder(var binding: PopularItemsBinding) : ViewHolder(binding.root)

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
        return categoryMealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealsViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView).load(categoryMealsList[position].strMealThumb)
                .into(popularMealIV)

        }

    }
}