package com.example.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.pojo.CategoriesMealsList
import com.example.foodapp.pojo.CategoryList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel : ViewModel() {
    private var mealsListLiveData= MutableLiveData<List<CategoryList.CategoryMeal>>()

    fun getMealsByCategory(category: String) {
        RetrofitInstance.api.getMealsByCategory(category)
            .enqueue(object :Callback<CategoryList>{
                override fun onResponse(
                    call: Call<CategoryList>,
                    response: Response<CategoryList>
                ) {
                    if (response.body()!=null){
                        mealsListLiveData.value = response.body()!!.meals
                    }
                }

                override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    Log.d("error in CategoryMeals ViewModel","$t")

                }
            })
    }

    fun observableMealsListLiveData():LiveData<List<CategoryList.CategoryMeal>>{
        return mealsListLiveData
    }

}