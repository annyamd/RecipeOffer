package com.example.recipeoffer.data.network

import com.example.recipeoffer.data.model.RecipeInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("recipes/findByIngredients")
    suspend fun getRecipes(@Query("ingredients") ingredients: String,
                           @Query("number") number: Int = 1,
                           @Query("ranking") ranking: Int = 1) : Response<List<RecipeInfo>>

}
