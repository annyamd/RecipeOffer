package com.example.recipeoffer.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeoffer.data.model.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Insert
    suspend fun insertIngredient(ingredient: Ingredient)

    @Query("DELETE FROM ingredients")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM ingredients")
    fun getAll() : Flow<List<Ingredient>>

}