package com.example.recipeoffer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipeoffer.RecipeApplication
import com.example.recipeoffer.databinding.FragmentRecipeInfoBinding
import com.example.recipeoffer.viewmodel.RecipeInfoViewModel

class RecipeInfoFragment : Fragment() {

    private var binding: FragmentRecipeInfoBinding? = null
    private val model: RecipeInfoViewModel by viewModels {
        RecipeInfoViewModel.RecipeInfoModelFactory((activity?.application
                as RecipeApplication).recipeInfoRepository, (activity?.application
                as RecipeApplication).ingredientRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.updateRecipeInfo()
        model.recipeInfo.observe(this.viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding?.recipeNameTextView?.text = it[0].title
            } else {
                binding?.recipeNameTextView?.text = "No recipe found"
            }
        }
    }

}
