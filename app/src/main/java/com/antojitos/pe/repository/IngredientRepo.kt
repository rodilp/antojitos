package com.antojitos.pe.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antojitos.pe.model.Ingredients
import com.antojitos.pe.model.Nutritionals
import com.antojitos.pe.model.Recipes
import com.google.firebase.firestore.FirebaseFirestore

class IngredientRepo {

    fun getIngredientData(idRecipe:String): LiveData<MutableList<Ingredients>> {
        val mutableData = MutableLiveData<MutableList<Ingredients>>()
        FirebaseFirestore.getInstance().collection("ingredients").whereEqualTo("idRecipe",idRecipe).get().addOnSuccessListener { result ->
            val listData = mutableListOf<Ingredients>()
            for (document in result) {
                val idRecipe: String? = document.getString("idRecipe")
                val name: String? = document.getString("name")

                listData.add(Ingredients(idRecipe!!, name!!))
            }
            mutableData.value = listData
        }
        return mutableData
    }


    fun getNutritionalData(idRecipe:String): LiveData<MutableList<Nutritionals>> {
        val mutableData = MutableLiveData<MutableList<Nutritionals>>()
        FirebaseFirestore.getInstance().collection("nutritional").whereEqualTo("idRecipe",idRecipe).get().addOnSuccessListener { result ->
            val listData = mutableListOf<Nutritionals>()
            for (document in result) {
                val idRecipe: String? = document.getString("idRecipe")
                val name: String? = document.getString("name")
                val order: Int? = document.get("order").toString().toInt()
                listData.add(Nutritionals(idRecipe!!, name!!,order!!))
            }
            mutableData.value = listData
        }
        return mutableData
    }
}