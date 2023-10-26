package com.example.foodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapters.MealsAdapter
import com.example.foodapp.databinding.ActivityCategoryMealsBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.pojo.CategoriesMealsList
import com.example.foodapp.pojo.CategoryList
import com.example.foodapp.viewmodels.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryMealsVM: CategoryMealsViewModel
    private lateinit var mealsAdapter: MealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mealsAdapter= MealsAdapter()
        categoryMealsVM = ViewModelProviders.of(this)[CategoryMealsViewModel::class.java]
        val intent = intent
        categoryMealsVM.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        observeCategoryMeals()
        setupMealRecyclerList()

    }

    private fun setupMealRecyclerList() {
        binding.mealsRv.layoutManager=GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        binding.mealsRv.adapter=mealsAdapter
    }

    private fun observeCategoryMeals() {
        categoryMealsVM.observableMealsListLiveData()
            .observe(this,object :Observer<List<CategoryList.CategoryMeal>> {
                override fun onChanged(value: List<CategoryList.CategoryMeal>) {
                    Log.d("some",value.toString())
                    binding.mealscountTv.text=value.size.toString()
                    mealsAdapter.setMealsList(value)
                }

            })
    }

}