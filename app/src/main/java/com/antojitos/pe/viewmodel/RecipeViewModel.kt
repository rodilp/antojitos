package com.antojitos.pe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antojitos.pe.model.Category
import com.antojitos.pe.model.Recipes
import com.antojitos.pe.repository.CategoryRepo
import com.antojitos.pe.repository.RecipeRepo

class RecipeViewModel: ViewModel() {
    private val repo = RecipeRepo()

    fun fetchRecipes(): LiveData<MutableList<Recipes>> {
        val mutableData= MutableLiveData<MutableList<Recipes>>()
        repo.getRecipesData().observeForever {
            mutableData.value = it
        }
        return  mutableData
    }


    fun fetchTopRecipes(): LiveData<MutableList<Recipes>> {
        val mutableData= MutableLiveData<MutableList<Recipes>>()
        repo.getTopRecipesData().observeForever {
            mutableData.value = it
        }
        return  mutableData
    }


    fun fetchOneRecipeById(idDocument:String): Recipes {
        return repo.getOneRecipeData(idDocument)
    }
}