package com.example.foodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.foodapp.R
import com.example.foodapp.db.MealDB
import com.example.foodapp.viewmodels.HomeViewModel
import com.example.foodapp.viewmodels.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //    private  var binding:
    val homeViewModel: HomeViewModel by lazy {
        val mealDB: MealDB = MealDB.getInstance(this)
        val homeviewmodelfactory: HomeViewModelFactory = HomeViewModelFactory(mealDB)
        ViewModelProvider(this, homeviewmodelfactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigationView, navController)
    }
}