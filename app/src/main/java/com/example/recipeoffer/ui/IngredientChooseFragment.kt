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
import com.example.recipeoffer.ui.adapters.ChooseIngredientsAdapter
import com.example.recipeoffer.viewmodel.IngredientViewModel

class IngredientChooseFragment : Fragment(), ChooseIngredientsAdapter.ItemClickListener {

    private var viewBinding: FragmentChooseIngredientBinding? = null
    private val viewModel: IngredientViewModel by activityViewModels {
        IngredientViewModel.IngredientViewModelFactory(
            (activity?.application
                    as RecipeApplication).ingredientRepository
        )
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
        val adapter = ChooseIngredientsAdapter(
            viewModel.ingredients.value ?: emptyList(),
            this
        )

        viewBinding?.run {
            ingredientListRv.adapter = adapter
            ingredientListRv.layoutManager = LinearLayoutManager(context)

            addIngrButton.setOnClickListener {
                val input = ingrEditText.text.toString().trim()
                if (input == "") {
                    Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
                } else {
                    val listSize = viewModel.ingredients.value?.size ?: 0
                    if (listSize <= 10) {
                        viewModel.insert(Ingredient(name = input))
                        ingrEditText.text?.clear()
                    } else {
                        Toast.makeText(context, "10 ingredients is maximum", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            searchButton.setOnClickListener {
                viewModel.isNeedToSearch.value = true
            }

            viewModel.ingredients.observe(this@IngredientChooseFragment.viewLifecycleOwner) { newList ->
                adapter.ingredients = newList
                listNameTextView.visibility =
                    if (viewModel.ingredients.value?.isEmpty() == true) View.INVISIBLE else View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onCancelClicked(ingredient: Ingredient) {
        viewModel.delete(ingredient)
    }

}
