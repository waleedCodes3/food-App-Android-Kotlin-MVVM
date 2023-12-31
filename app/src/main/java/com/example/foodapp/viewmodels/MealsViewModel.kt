package com.example.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.db.MealDB
import com.example.foodapp.pojo.MealsList
import com.example.foodapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MealsViewModel(var mealDB:MealDB?) : ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<MealsList.Meal>()
     fun getMealsDetails(id: String) {
        RetrofitInstance.api.getMealsDetails(id).enqueue(object : retrofit2.Callback<MealsList> {
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                if (response.body() != null) {
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.d("failed", "Failed to getMealsDetails")
            }

        })
    }
    fun observableMealDetailsLiveData():LiveData<MealsList.Meal>{
        return mealDetailsLiveData
    }

    fun insertMeal(meal:MealsList.Meal){
        viewModelScope.launch {
            mealDB!!.mealDAO().insertMeal(meal);
        }

    }



}