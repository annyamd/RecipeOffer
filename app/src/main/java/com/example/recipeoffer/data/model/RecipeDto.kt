package com.example.recipeoffer.data.model


data class RecipeOfferDto(
    val id: Int,
    val title: String,
    val image: String,
    val missedIngredientCount: Int,
    val missedIngredients: List<IngredientInfoDto>,
    val usedIngredientCount: Int,
    val usedIngredients: List<IngredientInfoDto>
)

data class IngredientInfoDto(
    val id: Int,
    val name: String,
    val amount: Double,
    val unitShort: String,
    val original: String
)

data class RecipeDescriptionDto(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String
)

data class RecipeInstructionsDto(
    val name: String,
    val steps: List<RecipeStepDto>
)

data class RecipeStepDto(
    val number: Int,
    val step: String
)
