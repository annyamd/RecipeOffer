package com.example.recipeoffer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.recipeoffer.data.model.RecipeInstructionStep
import com.example.recipeoffer.databinding.RecipeStepsItemBinding

class RecipeStepsAdapter : Adapter<RecipeStepsAdapter.ViewHolder>() {

    var steps: List<RecipeInstructionStep> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: RecipeStepsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeStepsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return steps.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            val stepNumber = position + 1
            number.text = stepNumber.toString()
            ingredientItemName.text = steps[position].actionDesc
        }
    }

}
