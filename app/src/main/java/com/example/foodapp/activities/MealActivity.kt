package com.example.foodapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ActivityMealBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.pojo.MealsList
import com.example.foodapp.viewmodels.MealsViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealDetailsVM: MealsViewModel
    private lateinit var youtubeLink:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onLoading()
        mealDetailsVM = MealsViewModel()
        getMealInfoFromIntent()
        setInformationInView()
        mealDetailsVM.getMealsDetails(mealId)
        observeMealsDetailsLiveData()
        onyoutubeImgClicked()
    }

    private fun onyoutubeImgClicked() {
        binding.youtubeIV.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observeMealsDetailsLiveData() {
        mealDetailsVM.observableMealDetailsLiveData()
            .observe(this, object : Observer<MealsList.Meal> {
                override fun onChanged(value: MealsList.Meal) {
                    onLoaded()
                    binding.categoryTV.text = "Category: ${value.strCategory}"
                    binding.areaTV.text = "Area ${value.strArea}"
                    binding.instructionsSet.text = value.strInstructions

                }

            })
    }

    private fun setInformationInView() {
        Glide.with(applicationContext).load(mealThumb).into(binding.imgMealDetail)
        binding.mealtitle.title = mealName
    }

    private fun getMealInfoFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun onLoading() {
        binding.areaTV.visibility = View.INVISIBLE
        binding.youtubeIV.visibility = View.INVISIBLE
        binding.progressbar.visibility = View.VISIBLE
        binding.areaTV.visibility = View.INVISIBLE
        binding.categoryTV.visibility=View.INVISIBLE
        binding.favFab.visibility=View.INVISIBLE
    }

    private fun onLoaded() {
        binding.areaTV.visibility = View.VISIBLE
        binding.youtubeIV.visibility = View.VISIBLE
        binding.progressbar.visibility = View.INVISIBLE
        binding.areaTV.visibility = View.VISIBLE
        binding.categoryTV.visibility=View.VISIBLE
        binding.favFab.visibility=View.VISIBLE
    }


}