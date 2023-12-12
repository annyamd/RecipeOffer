package com.example.recipeoffer.data.util

import com.example.recipeoffer.data.model.RecipeDescriptionDto
import com.example.recipeoffer.data.model.RecipeDetails
import com.example.recipeoffer.data.model.RecipeInstructionsDto
import com.example.recipeoffer.data.model.RecipeOfferDto

interface RecipeDtoMapper {
    fun map(
        offer: RecipeOfferDto,
        desc: RecipeDescriptionDto,
        instructions: List<RecipeInstructionsDto>
    ) : RecipeDetails

}
