package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.CategoriesItemBinding
import com.example.foodapp.pojo.CategoriesMealsList

class AllCategoiesAdapater : RecyclerView.Adapter<AllCategoiesAdapater.CategoriesViewHolder>() {
    private var allcategoriesList = ArrayList<CategoriesMealsList.Category>()
    var itemClick:((CategoriesMealsList.Category)->Unit)? = null
    fun setAllCategories(categories: ArrayList<CategoriesMealsList.Category>) {
        allcategoriesList = categories
        notifyDataSetChanged()

    }

    inner class CategoriesViewHolder(var binding: CategoriesItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return allcategoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView).load(allcategoriesList[position].strCategoryThumb)
                .into(categoryImage)
            categoryTitle.text = allcategoriesList[position].strCategory
        }
        holder.itemView.setOnClickListener {
            itemClick!!.invoke(allcategoriesList[position])
        }

    }
}