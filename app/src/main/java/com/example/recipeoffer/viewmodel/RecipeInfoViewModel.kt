package com.example.recipeoffer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.recipeoffer.data.model.Ingredient
import com.example.recipeoffer.data.model.RecipeDetails
import com.example.recipeoffer.data.repository.RecipeInfoRepository
import com.example.recipeoffer.data.repository.IngredientRepository
import kotlinx.coroutines.flow.first

class RecipeInfoViewModel(
    private val repository: RecipeInfoRepository,
    private val ingredientRepository: IngredientRepository
) : ViewModel() {

    var recipeInfo: LiveData<RecipeDetails?> = liveData {
        val res = updateRecipeInfo()
        if (res != null) {
            emit(res)
        }
    }


    private suspend fun updateRecipeInfo(): RecipeDetails? {
        val list: List<Ingredient> = ingredientRepository.ingredients.first()
        return repository.loadRecipeByIngredients(list)
    }

    class RecipeInfoModelFactory(
        private val repository: RecipeInfoRepository,
        private val ingredientRepository: IngredientRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipeInfoViewModel::class.java)) {
                return RecipeInfoViewModel(repository, ingredientRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}