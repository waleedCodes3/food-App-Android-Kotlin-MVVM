package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.MealItemBinding
import com.example.foodapp.pojo.CategoryList

class MealsAdapter : RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    private var mealsList = listOf<CategoryList.CategoryMeal>()

    fun setMealsList(list: List<CategoryList.CategoryMeal>) {
        mealsList = list
        notifyDataSetChanged()
    }

    class MealsViewHolder(var binding: MealItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        return MealsViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.binding.apply {
            mealTitle.text = mealsList[position].strMeal
            Glide.with(holder.itemView).load(mealsList[position].strMealThumb)
                .into(mealImage)
        }
    }
}