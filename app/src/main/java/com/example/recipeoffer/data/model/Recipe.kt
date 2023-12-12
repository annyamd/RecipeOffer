package com.example.recipeoffer.data.model

data class RecipeDetails(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val readyInMinutes: Int,
    val sourceName: String,
    val usedIngredients: List<IngredientInfo>,
    val missedIngredients: List<IngredientInfo>,
    val instruction: List<RecipeInstructionStep>
)

data class IngredientInfo(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String,
    val amountUnit: String
)

data class RecipeInstructionStep(
    val stepNumber: Int,
    val actionDesc: String
)
