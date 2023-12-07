package com.example.recipeoffer.db.repository

import com.example.recipeoffer.db.dao.IngredientDao
import com.example.recipeoffer.model.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val ingredientDao: IngredientDao) {

    val ingredients: Flow<List<Ingredient>> = ingredientDao.getAll()

    suspend fun insert(ingredient: Ingredient) {
        ingredientDao.insertIngredient(ingredient)
    }

}