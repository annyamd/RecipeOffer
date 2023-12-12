package com.example.recipeoffer.data.repository

import com.example.recipeoffer.data.model.Ingredient
import com.example.recipeoffer.data.model.RecipeDescriptionDto
import com.example.recipeoffer.data.model.RecipeDetails
import com.example.recipeoffer.data.model.RecipeInstructionsDto
import com.example.recipeoffer.data.model.RecipeOfferDto
import com.example.recipeoffer.data.network.RecipesApi
import com.example.recipeoffer.data.util.GeneralRecipeDtoMapper
import com.example.recipeoffer.data.util.RecipeDtoMapper

class RecipeInfoRepository(private val recipesApi: RecipesApi) {

    private val mapper: RecipeDtoMapper = GeneralRecipeDtoMapper

    suspend fun loadRecipeByIngredients(ingredients: List<Ingredient>): RecipeDetails? {
        val offerRecipes = loadByIngredients(ingredients) ?: return null

        //show only first recipe
        val offerRecipe = offerRecipes[0]
        val details = loadRecipeDesc(offerRecipe.id) ?: return null
        val steps = loadRecipeInstructions(offerRecipe.id) ?: return null

        return mapper.map(offerRecipe, details, steps)
    }

    private suspend fun loadByIngredients(ingredients: List<Ingredient>): List<RecipeOfferDto>? {
        val ingredientsAsString = ingredients.joinToString(separator = ",") { it.name }
        val response = recipesApi.getRecipes(ingredientsAsString)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    private suspend fun loadRecipeDesc(id: Int): RecipeDescriptionDto? {
        val response = recipesApi.getRecipeDescription(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    private suspend fun loadRecipeInstructions(id: Int): List<RecipeInstructionsDto>? {
        val response = recipesApi.getRecipeInstructions(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

}
