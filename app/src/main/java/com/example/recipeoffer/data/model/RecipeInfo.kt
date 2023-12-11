package com.example.recipeoffer.data.model


data class RecipeInfo(
    val id: Int,
    val title: String,
    val image: String,
    val missedIngredientCount: Int,
    val missedIngredients: List<IngredientInfo>,
    val usedIngredientCount: Int,
    val usedIngredients: List<IngredientInfo>
)

data class IngredientInfo(
    val id: Int,
    val name: String,
    val amount: Double,
    val unitShort: String,
    val original: String
)