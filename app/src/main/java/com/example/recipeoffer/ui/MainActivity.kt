package com.example.recipeoffer.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.recipeoffer.R
import com.example.recipeoffer.RecipeApplication
import com.example.recipeoffer.viewmodel.IngredientViewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: IngredientViewModel by viewModels {
        IngredientViewModel.IngredientViewModelFactory((application
                as RecipeApplication).ingredientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<IngredientChooseFragment>(R.id.fragment_container)
                addToBackStack(null)
            }
        }

        viewModel.isNeedToSearch.observe(this) {
            if (it) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<RecipeInfoFragment>(R.id.fragment_container)
                    addToBackStack(null)
                }
                viewModel.isNeedToSearch.value = false
            }
        }

    }
}