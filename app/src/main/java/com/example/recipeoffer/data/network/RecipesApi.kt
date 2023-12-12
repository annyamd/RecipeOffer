package com.example.recipeoffer.data.network

import com.example.recipeoffer.data.model.RecipeDescriptionDto
import com.example.recipeoffer.data.model.RecipeInstructionsDto
import com.example.recipeoffer.data.model.RecipeOfferDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {

    @GET("recipes/findByIngredients")
    suspend fun getRecipes(@Query("ingredients") ingredients: String,
                           @Query("number") number: Int = 1,
                           @Query("ranking") ranking: Int = 1) : Response<List<RecipeOfferDto>>

    @GET("recipes/{id}/information")
    suspend fun getRecipeDescription(@Path("id") id: Int) : Response<RecipeDescriptionDto>

    @GET("recipes/{id}/analyzedInstructions")
    suspend fun getRecipeInstructions(@Path("id") id: Int) : Response<List<RecipeInstructionsDto>>

}
