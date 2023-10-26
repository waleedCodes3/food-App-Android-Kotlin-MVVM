package com.example.foodapp.pojo

data class CategoriesMealsList(
    val categories: List<Category>
) {
    data class Category(
        val idCategory: String,
        val strCategory: String,
        val strCategoryDescription: String,
        val strCategoryThumb: String
    )
}