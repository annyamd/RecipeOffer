package com.example.recipeoffer

import android.app.Application
import com.example.recipeoffer.db.AppDatabase
import com.example.recipeoffer.db.repository.IngredientRepository

class RecipeApplication : Application() {

    val database by lazy {
        AppDatabase.getDatabase(this)
    }

    val repository by lazy {
        IngredientRepository(database.getIngredientDao())
    }

}