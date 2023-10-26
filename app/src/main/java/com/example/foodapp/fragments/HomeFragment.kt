package com.example.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.viewmodels.HomeViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.activities.CategoryMealsActivity
import com.example.foodapp.activities.MealActivity
import com.example.foodapp.adapters.AllCategoiesAdapater
import com.example.foodapp.adapters.MostPopularMealsAdapter
import com.example.foodapp.pojo.CategoriesMealsList
import com.example.foodapp.pojo.CategoryList
import com.example.foodapp.pojo.MealsList

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var randomMeal: MealsList.Meal
    private lateinit var popularMealsAdapter: MostPopularMealsAdapter
    private lateinit var categoiesAdapater: AllCategoiesAdapater

    companion object {
        const val MEAL_ID = "someMealId"
        const val MEAL_NAME = "someMealName"
        const val MEAL_THUMB = "someMealThumb"
        const val CATEGORY_NAME = "some Category Name"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        popularMealsAdapter = MostPopularMealsAdapter()
        categoiesAdapater = AllCategoiesAdapater()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        CoroutineScope(Dispatchers.IO).launch {

        Toast.makeText(requireContext(), "some", Toast.LENGTH_SHORT).show()
        homeViewModel.getRandomMeal()
        observeRandomMeal()
        onRandomMealClicked()
        homeViewModel.getPopularMealsItems()
        observePopularMealItems()
        preparePopularItemsRecyclerView()
        homeViewModel.getAllCategories()
        observeAllCategoriesItems()
        prepareAllCategoriesRecyclerView()
        categoriesItemClicked()


    }

    private fun categoriesItemClicked() {
        categoiesAdapater.itemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME,category.strCategory)
            startActivity(intent)

        }
    }

    private fun observeAllCategoriesItems() {
        homeViewModel.observerAllCategoriesList()
            .observe(viewLifecycleOwner, object : Observer<List<CategoriesMealsList.Category>> {
                override fun onChanged(value: List<CategoriesMealsList.Category>) {
                    categoiesAdapater.setAllCategories(value as ArrayList<CategoriesMealsList.Category>)
                }
            })
    }

    private fun prepareAllCategoriesRecyclerView() {
        binding.categoryRv.layoutManager = GridLayoutManager(this.context, 3)
        binding.categoryRv.adapter = categoiesAdapater
    }

    private fun preparePopularItemsRecyclerView() {
        binding.rvPopularItems.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularItems.adapter = popularMealsAdapter


    }

    private fun observePopularMealItems() {
        homeViewModel.observerPopularMealsList()
            .observe(viewLifecycleOwner, object : Observer<List<CategoryList.CategoryMeal>> {
                override fun onChanged(value: List<CategoryList.CategoryMeal>) {
                    popularMealsAdapter.setMeals(value as ArrayList<CategoryList.CategoryMeal>)
                }
            })
    }

    private fun onRandomMealClicked() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }


    private fun observeRandomMeal() {
        homeViewModel.observeRandomMealLiveData()
            .observe(viewLifecycleOwner, object : Observer<MealsList.Meal> {
                override fun onChanged(value: MealsList.Meal) {
                    Glide.with(this@HomeFragment).load(value.strMealThumb)
                        .into(binding.randommealiv)
                    this@HomeFragment.randomMeal = value
                }

            })
    }

//    }


}