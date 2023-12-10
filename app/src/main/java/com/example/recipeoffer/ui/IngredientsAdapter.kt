package com.example.recipeoffer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeoffer.databinding.IngredientsListItemBinding
import com.example.recipeoffer.data.model.Ingredient

class IngredientsAdapter(ingredients: List<Ingredient>, private val clickListener: ItemClickListener)
    : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){

    var ingredients = ingredients
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: IngredientsListItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IngredientsListItemBinding.inflate(inflater, parent, false)
        val vh = ViewHolder(binding)
        binding.ingrCancelImage.setOnClickListener {
            val pos = vh.adapterPosition
            clickListener.onCancelClicked(ingredients[pos])
        }
        return vh
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.ingredientItemName.text = ingredients[position].name
    }

    interface ItemClickListener {
        fun onCancelClicked(ingredient: Ingredient)
    }
}