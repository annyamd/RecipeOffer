package com.example.recipeoffer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipeoffer.data.model.Ingredient
import com.example.recipeoffer.data.repository.RecipeInfoRepository
import com.example.recipeoffer.data.model.RecipeInfo
import com.example.recipeoffer.data.repository.IngredientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeInfoViewModel(private val repository: RecipeInfoRepository,
                          private val ingredientRepository: IngredientRepository) : ViewModel() {

    var recipeInfo: LiveData<List<RecipeInfo>> = repository.recipeInfo

    fun updateRecipeInfo() {
        viewModelScope.launch(Dispatchers.IO){
            val list: List<Ingredient> = ingredientRepository.ingredients.first()
            repository.getRecipeByIngredients(list)
            recipeInfo = repository.recipeInfo
        }

    }

    class RecipeInfoModelFactory(private val repository: RecipeInfoRepository,
                                 private val ingredientRepository: IngredientRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipeInfoViewModel::class.java)) {
                return RecipeInfoViewModel(repository, ingredientRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}