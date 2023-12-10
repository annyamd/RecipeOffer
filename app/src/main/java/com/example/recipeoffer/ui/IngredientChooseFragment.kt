package com.example.recipeoffer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeoffer.RecipeApplication
import com.example.recipeoffer.databinding.FragmentChooseIngredientBinding
import com.example.recipeoffer.data.model.Ingredient
import com.example.recipeoffer.viewmodel.IngredientViewModel

class IngredientChooseFragment : Fragment() {

    private var viewBinding: FragmentChooseIngredientBinding? = null
    private val viewModel: IngredientViewModel by activityViewModels {
        IngredientViewModel.IngredientViewModelFactory((activity?.application
                as RecipeApplication).ingredientRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentChooseIngredientBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = IngredientsAdapter(
            viewModel.ingredients.value ?: emptyList()
        )
        viewBinding?.ingredientListRv?.adapter = adapter
        viewBinding?.ingredientListRv?.layoutManager = LinearLayoutManager(context)

        viewBinding?.addIngrButton?.setOnClickListener {
            val input = viewBinding?.ingrEditText?.text.toString()
            if (input != "") {
                if ((viewModel.ingredients.value?.size ?: 0) <= 10) {
                    viewModel.insert(Ingredient(name = input))
                    viewBinding?.ingrEditText?.text?.clear()
                } else {
                    Toast.makeText(context, "10 ingredients is maximum", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
            }
        }

        viewBinding?.searchButton?.setOnClickListener {
            viewModel.isNeedToSearch.value = true
        }

        viewModel.ingredients.observe(this.viewLifecycleOwner) { newList ->
            adapter.ingredients = newList
            viewBinding?.listNameTextView?.visibility =
                if (viewModel.ingredients.value?.isEmpty() == true) View.INVISIBLE else View.VISIBLE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}