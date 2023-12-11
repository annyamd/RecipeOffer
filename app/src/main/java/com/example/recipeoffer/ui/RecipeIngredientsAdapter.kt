package com.example.recipeoffer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.recipeoffer.data.model.IngredientInfo
import com.example.recipeoffer.databinding.RecipeIngredientsItemBinding

class RecipeIngredientsAdapter :
    Adapter<RecipeIngredientsAdapter.ViewHolder>() {

    var ingredients: List<IngredientInfo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: RecipeIngredientsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeIngredientsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.ingredientItemName.text = ingredients[position].original
    }

}