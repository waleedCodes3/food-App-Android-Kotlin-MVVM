package com.example.foodapp.retrofit

import com.example.foodapp.pojo.MealsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MealAPI {
    @GET("random.php")
     fun getRandomMeal(): Call<MealsList>
     @GET("lookup.php")
     fun getMealsDetails(@Query("i")id:String):Call<MealsList>

}