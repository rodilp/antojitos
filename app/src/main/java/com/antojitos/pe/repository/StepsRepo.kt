package com.antojitos.pe.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antojitos.pe.model.Steps
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class StepsRepo {

    fun getStepsData(idRecipe:String): LiveData<MutableList<Steps>> {
        val mutableData = MutableLiveData<MutableList<Steps>>()
        FirebaseFirestore.getInstance().collection("steps")
            .whereEqualTo("idRecipe",idRecipe)
           .get().addOnSuccessListener { result ->
            val listData = mutableListOf<Steps>()
            for (document in result) {
                val idRecipe: String? = document.getString("idRecipe")
                val title: String? = document.getString("title")
                val description: String? = document.getString("description")

                listData.add(Steps(idRecipe!!, title!!,description!!))
            }
            mutableData.value = listData
        }
        return mutableData
    }
}