package com.example.recipeoffer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeoffer.R
import com.example.recipeoffer.RecipeApplication
import com.example.recipeoffer.data.model.IngredientInfo
import com.example.recipeoffer.databinding.FragmentRecipeInfoBinding
import com.example.recipeoffer.ui.adapters.RecipeIngredientsAdapter
import com.example.recipeoffer.ui.adapters.RecipeStepsAdapter
import com.example.recipeoffer.viewmodel.RecipeInfoViewModel
import com.squareup.picasso.Picasso
import java.lang.IllegalStateException

class RecipeInfoFragment : Fragment() {

    private var binding: FragmentRecipeInfoBinding? = null

    private val model: RecipeInfoViewModel by viewModels {
        val application = requireActivity().application as? RecipeApplication
            ?: throw IllegalStateException("Application must implementation RecipeApplication")
        RecipeInfoViewModel.RecipeInfoModelFactory(
            application.recipeInfoRepository,
            application.ingredientRepository
        )
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

        val ingredientsAdapter = RecipeIngredientsAdapter()
        val stepsAdapter = RecipeStepsAdapter()

        binding?.run {
            recipeIngredientsRv.layoutManager = LinearLayoutManager(context)
            recipeIngredientsRv.adapter = ingredientsAdapter
            recipeStepsRv.layoutManager = LinearLayoutManager(context)
            recipeStepsRv.adapter = stepsAdapter
        }

        model.recipeInfo.observe(this.viewLifecycleOwner) { recipe ->
            binding?.run {
                if (recipe != null) {
                    Log.i("retrofit", "on recipe changed")
                    recipeNameTextView.text = recipe.title
                    cookingTimeTextView.text = getString(R.string.time_min, recipe.readyInMinutes)
                    ingredientsAdapter.ingredients =
                        getFullListOfIngredients(recipe.missedIngredients, recipe.usedIngredients)
                    stepsAdapter.steps =
                        recipe.instruction

                    Picasso.get()
                        .load(recipe.imageUrl.replace("312x231", "636x393"))
                        .fit()
                        .transform(DeleteExtraBorderTransformation())
                        .centerCrop()
                        .into(recipeImageView)

                } else {
                    recipeNameTextView.text = getString(R.string.no_recipe_found_message)
                }
            }
        }
    }

    private fun getFullListOfIngredients(
        missed: List<IngredientInfo>,
        used: List<IngredientInfo>,
    ): List<IngredientInfo> {
        val list = mutableListOf<IngredientInfo>()
        list.addAll(missed)
        list.addAll(used)
        return list
    }

}
