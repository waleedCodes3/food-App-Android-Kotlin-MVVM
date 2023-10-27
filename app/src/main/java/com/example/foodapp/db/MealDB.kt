package com.example.foodapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.pojo.MealsList

@Database(entities = [MealsList.Meal::class], version = 2)
@TypeConverters(MealTypeConvertor::class)
abstract class MealDB : RoomDatabase() {
    abstract fun mealDAO(): MealDAO

    companion object {
        @Volatile
        var INSTANCE: MealDB? = null

        @Synchronized
        fun getInstance(context: Context): MealDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MealDB::class.java, "meal.db")
                    .fallbackToDestructiveMigration().build()
            }

            return INSTANCE as MealDB
        }


    }
}