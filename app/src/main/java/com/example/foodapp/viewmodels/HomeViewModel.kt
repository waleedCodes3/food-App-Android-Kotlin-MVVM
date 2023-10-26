package com.example.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.pojo.CategoriesMealsList
import com.example.foodapp.pojo.CategoryList
import com.example.foodapp.pojo.MealsList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var randomMealLiveData = MutableLiveData<MealsList.Meal>()
    var popularMealsListLiveData = MutableLiveData<List<CategoryList.CategoryMeal>>()
    var categoriesMealsListLiveData = MutableLiveData<List<CategoriesMealsList.Category>>()
    fun getRandomMeal() {
            RetrofitInstance.api.getRandomMeal().enqueue(object : retrofit2.Callback<MealsList> {
                override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {


                    response.body()?.let {
                        randomMealLiveData.value = it.meals[0]
                    }
                }

                override fun onFailure(call: Call<MealsList>, t: Throwable) {
                    Log.d("error in homeViewModel","$t")
                }


            })
    }

    fun getPopularMealsItems() {
        RetrofitInstance.api.getPopularMealsItems("Seafood")
            .enqueue(object : Callback<CategoryList> {
                override fun onResponse(
                    call: Call<CategoryList>,
                    response: Response<CategoryList>
                ) {
                    if (response.body() != null) {
                        popularMealsListLiveData.value = response.body()!!.meals

                    }
                }

                override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    Log.d("error", "failed to get popularMealsItems")
                }

            })
    }

    fun getAllCategories() {
        RetrofitInstance.api.getAllCategoriesItems()
            .enqueue(object : Callback<CategoriesMealsList> {
                override fun onResponse(
                    call: Call<CategoriesMealsList>,
                    response: Response<CategoriesMealsList>
                ) {
                    if (response.body()!=null)
                    {
                        categoriesMealsListLiveData.value=response.body()!!.categories
                    }else{
                        println("here")
                    }
                }

                override fun onFailure(call: Call<CategoriesMealsList>, t: Throwable) {
                    Log.d("error", "failed to get all categories List")

                }
            })
    }
    fun observerAllCategoriesList():LiveData<List<CategoriesMealsList.Category>>{
        return categoriesMealsListLiveData
    }

    fun observerPopularMealsList(): LiveData<List<CategoryList.CategoryMeal>> {
        return popularMealsListLiveData
    }

    fun observeRandomMealLiveData(): LiveData<MealsList.Meal> {
        return randomMealLiveData
    }
}