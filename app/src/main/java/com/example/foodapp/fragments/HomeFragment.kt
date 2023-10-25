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
import com.bumptech.glide.Glide
import com.example.foodapp.activities.MealActivity
import com.example.foodapp.pojo.MealsList

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var randomMeal:MealsList.Meal

    companion object{
        const val MEAL_ID="someMealId"
        const val MEAL_NAME="someMealName"
        const val MEAL_THUMB="someMealThumb"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
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


    }
    private fun onRandomMealClicked(){
        binding.randomMealCard.setOnClickListener{
            val intent=Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }


    private fun observeRandomMeal() {
        homeViewModel.observeRandomMealLiveData()
            .observe(viewLifecycleOwner, object : Observer<MealsList.Meal> {
                override fun onChanged(value: MealsList.Meal) {
                    Glide.with(this@HomeFragment).load(value.strMealThumb)
                        .into(binding.randommealiv)
                    this@HomeFragment.randomMeal=value
                }

            })
    }

//    }


}