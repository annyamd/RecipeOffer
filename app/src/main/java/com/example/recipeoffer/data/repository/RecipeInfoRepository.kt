package com.example.recipeoffer.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.recipeoffer.data.model.Ingredient
import com.example.recipeoffer.data.model.RecipeInfo
import com.example.recipeoffer.data.network.RecipesApi

class RecipeInfoRepository(private val recipesApi: RecipesApi) {

    var recipeInfo = MutableLiveData<List<RecipeInfo>>()

    suspend fun getRecipeByIngredients(ingredients: List<Ingredient>) {

        val ingredientsAsString = ingredients.joinToString(separator = ",")
        val response = recipesApi.getRecipes(ingredientsAsString)
        if (response.isSuccessful) {
            recipeInfo.postValue(response.body())
            Log.i("retrofit", "Loaded ${response.body()}")
        } else {
            Log.w("retrofit", "failure to load recipe info")
        }
    }

}