package com.example.foodapp.retrofit

import com.example.foodapp.pojo.CategoriesMealsList
import com.example.foodapp.pojo.CategoryList
import com.example.foodapp.pojo.MealsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MealAPI {
    @GET("random.php")
     fun getRandomMeal(): Call<MealsList>
     @GET("lookup.php")
     fun getMealsDetails(@Query("i")id:String):Call<MealsList>
     @GET("filter.php?")
     fun getPopularMealsItems(@Query("c")category:String):Call<CategoryList>
     @GET("categories.php")
     fun getAllCategoriesItems():Call<CategoriesMealsList>
     @GET("filter.php")
     fun getMealsByCategory(@Query("c")category:String):Call<CategoryList >


}