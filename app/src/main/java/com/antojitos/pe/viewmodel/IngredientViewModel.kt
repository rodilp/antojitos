package com.antojitos.pe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antojitos.pe.model.Ingredients
import com.antojitos.pe.model.Nutritionals
import com.antojitos.pe.model.Recipes
import com.antojitos.pe.repository.IngredientRepo
import com.antojitos.pe.repository.RecipeRepo

class IngredientViewModel : ViewModel() {
    private val repo = IngredientRepo()

    fun fetchIngredientsByRecipe(idRecipe:String ): LiveData<MutableList<Ingredients>> {
        val mutableData= MutableLiveData<MutableList<Ingredients>>()
        repo.getIngredientData(idRecipe).observeForever {
            mutableData.value = it
        }
        return  mutableData
    }

    fun fetchNutritionalByRecipe(idRecipe:String ): LiveData<MutableList<Nutritionals>> {
        val mutableData= MutableLiveData<MutableList<Nutritionals>>()
        repo.getNutritionalData(idRecipe).observeForever {
            mutableData.value = it
        }
        return  mutableData
    }
}