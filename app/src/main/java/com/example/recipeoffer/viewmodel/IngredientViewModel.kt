package com.example.recipeoffer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeoffer.data.model.Ingredient
import com.example.recipeoffer.data.repository.IngredientRepository
import kotlinx.coroutines.launch

class IngredientViewModel(private val repository: IngredientRepository) : ViewModel() {

    val ingredients: LiveData<List<Ingredient>> = repository.ingredients.asLiveData()

    var isNeedToSearch: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun insert(ingredient: Ingredient) = viewModelScope.launch {
        repository.insert(ingredient)
    }

    class IngredientViewModelFactory(private val repository: IngredientRepository)
        :  ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IngredientViewModel::class.java)) {
                return IngredientViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}