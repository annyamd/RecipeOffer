package com.example.recipeoffer.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeoffer.model.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Insert
    suspend fun insertIngredient(ingredient: Ingredient)

    @Query("DELETE FROM ingredients")
    suspend fun deleteAll()

    @Query("SELECT * FROM ingredients")
    fun getAll() : Flow<List<Ingredient>>

}