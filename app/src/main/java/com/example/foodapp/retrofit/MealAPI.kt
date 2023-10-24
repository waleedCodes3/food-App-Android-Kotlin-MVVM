package com.example.foodapp.retrofit

import android.telecom.Call
import com.example.foodapp.pojo.MealsList
import retrofit2.Response
import retrofit2.http.GET

interface MealAPI {
    @GET("random.php")
    fun getRandomMeal():Response<MealsList>
}