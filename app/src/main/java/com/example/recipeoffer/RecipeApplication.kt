package com.example.recipeoffer

import android.app.Application
import com.example.recipeoffer.data.db.AppDatabase
import com.example.recipeoffer.data.network.SpoonacularService
import com.example.recipeoffer.data.repository.IngredientRepository
import com.example.recipeoffer.data.repository.RecipeInfoRepository

class RecipeApplication : Application() {

    val database by lazy {
        AppDatabase.getDatabase(this)
    }

    val ingredientRepository by lazy {
        IngredientRepository(database.getIngredientDao())
    }

    val recipeInfoRepository by lazy {
        RecipeInfoRepository(recipeApi)
    }

    val recipeApi by lazy {
        SpoonacularService().recipesApi
    }

}