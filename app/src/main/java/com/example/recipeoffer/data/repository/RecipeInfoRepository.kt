package com.example.recipeoffer.data.repository

import android.util.Log
import com.example.recipeoffer.data.model.Ingredient
import com.example.recipeoffer.data.model.RecipeInfo
import com.example.recipeoffer.data.network.RecipesApi

class RecipeInfoRepository(private val recipesApi: RecipesApi) {

    suspend fun loadRecipeByIngredients(ingredients: List<Ingredient>): List<RecipeInfo>? {
        val ingredientsAsString = ingredients.joinToString(separator = ",") { it.name }
        val response = recipesApi.getRecipes(ingredientsAsString)
        return if (response.isSuccessful) {
            Log.i("retrofit", "Loaded ${response.body()} \n for $ingredientsAsString \n")
            response.body()
        } else {
            Log.w("retrofit", "failure to load recipe info")
            null
        }
    }

}