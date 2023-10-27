package com.example.foodapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.pojo.MealsList

@Dao
interface MealDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealsList.Meal)

    @Delete
    suspend fun deleteMeal(meal: MealsList.Meal)

    @Query("SELECT * FROM  meal_info")
     fun getAllMeals(): LiveData<List<MealsList.Meal>>

}