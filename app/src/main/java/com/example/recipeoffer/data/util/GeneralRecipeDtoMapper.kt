package com.example.recipeoffer.data.util

import com.example.recipeoffer.data.model.IngredientInfo
import com.example.recipeoffer.data.model.IngredientInfoDto
import com.example.recipeoffer.data.model.RecipeDescriptionDto
import com.example.recipeoffer.data.model.RecipeDetails
import com.example.recipeoffer.data.model.RecipeInstructionStep
import com.example.recipeoffer.data.model.RecipeInstructionsDto
import com.example.recipeoffer.data.model.RecipeOfferDto

object GeneralRecipeDtoMapper : RecipeDtoMapper {

    override fun map(
        offer: RecipeOfferDto,
        desc: RecipeDescriptionDto,
        instructions: List<RecipeInstructionsDto>
    ): RecipeDetails {
        return RecipeDetails(
            desc.id,
            desc.title,
            desc.image,
            desc.readyInMinutes,
            desc.sourceName,
            mapIngredientsList(offer.usedIngredients),
            mapIngredientsList(offer.missedIngredients),
            mapInstructionsList(instructions)
        )
    }

    private fun mapIngredientsList(list: List<IngredientInfoDto>): List<IngredientInfo> {
        return list.map { IngredientInfo(it.id, it.name, it.amount, it.unitShort, it.original) }
    }

    private fun mapInstructionsList(list: List<RecipeInstructionsDto>): List<RecipeInstructionStep> {
        val mapped: MutableList<RecipeInstructionStep> = mutableListOf()
        for (i in list.indices) {
            val stepsFromSection = list[i].steps.map { RecipeInstructionStep(it.number, it.step) }
            mapped.addAll(stepsFromSection)
        }
        return mapped
    }
}
