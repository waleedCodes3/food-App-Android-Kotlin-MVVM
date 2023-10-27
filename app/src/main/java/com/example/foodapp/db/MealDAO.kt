package com.example.foodapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(){}

    @Delete
    suspend fun deleteMeal(){}
    @Query("SELECT * FROM  meal_info")
    suspend fun getAllMeals(){

    }
}