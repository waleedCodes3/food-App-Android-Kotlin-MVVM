package com.example.foodapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.pojo.MealsList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {
     var randomMealLiveData = MutableLiveData<MealsList.Meal>()
    fun getRandomMeal() {
        val response =
            RetrofitInstance.api.getRandomMeal().enqueue(object : retrofit2.Callback<MealsList> {
                override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {


                    response.body()?.let {
                        randomMealLiveData.value = it.meals[0]
                        var x = randomMealLiveData.value!!.strMealThumb.toString()
                    }
                }

                override fun onFailure(call: Call<MealsList>, t: Throwable) {
                }


            })
    }

    fun observeRandomMealLiveData(): LiveData<MealsList.Meal> {
        return randomMealLiveData
    }
}