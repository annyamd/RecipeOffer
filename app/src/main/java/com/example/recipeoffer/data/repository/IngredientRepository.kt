package com.example.recipeoffer.data.repository

import com.example.recipeoffer.data.db.dao.IngredientDao
import com.example.recipeoffer.data.model.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val ingredientDao: IngredientDao) {

    val ingredients: Flow<List<Ingredient>> = ingredientDao.getAll()

    suspend fun insert(ingredient: Ingredient) {
        ingredientDao.insertIngredient(ingredient)
    }

}