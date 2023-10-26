package com.example.foodapp.pojo

data class CategoryList(
    val meals: List<CategoryMeal>
) {
    data class CategoryMeal(
        val idMeal: String,
        val strMeal: String,
        val strMealThumb: String
    )
}