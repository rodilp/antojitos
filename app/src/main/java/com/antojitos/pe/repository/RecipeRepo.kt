package com.antojitos.pe.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antojitos.pe.model.Category
import com.antojitos.pe.model.Recipes
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class RecipeRepo {
    fun getRecipesData(): LiveData<MutableList<Recipes>> {
        val mutableData = MutableLiveData<MutableList<Recipes>>()
        FirebaseFirestore.getInstance().collection("recipes").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Recipes>()
            for (document in result) {
                //val cat : MutableMap<String, Any> = document.data
                val idDocument: String? = document.id
                val calories: String? = document.getString("calorie")
                val description: String? = document.getString("description")
                val idCategory: String? = document.getString("idCategory")
                val level: String? = document.getString("level")
                val likes: Int? = document.get("likes").toString().toInt()
                val time: String? = document.getString("time")
                val title: String? = document.getString("title")
                val uriImage: String? = document.getString("uriImage")
                val video: String? = document.getString("video")

                listData.add(Recipes(idDocument!!, calories!!,
                        description!!,
                        idCategory!!,
                        level!!,
                        likes!!,
                        time!!,
                        title!!,
                        uriImage!!,
                        video!!))
            }
            mutableData.value = listData
        }
        return mutableData
    }


    fun getTopRecipesData(): LiveData<MutableList<Recipes>> {
        val mutableData = MutableLiveData<MutableList<Recipes>>()
        FirebaseFirestore.getInstance().collection("recipes")
                .whereGreaterThan("likes", 50)
                .orderBy("likes", Query.Direction.DESCENDING).get().addOnSuccessListener { result ->

                    val listData = mutableListOf<Recipes>()
                    for (document in result) {

                        val idDocument: String? = document.id
                        val calories: String? = document.getString("calorie")
                        val description: String? = document.getString("description")
                        val idCategory: String? = document.getString("idCategory")
                        val level: String? = document.getString("level")
                        val likes: Int? = document.get("likes").toString().toInt()
                        val time: String? = document.getString("time")
                        val title: String? = document.getString("title")
                        val uriImage: String? = document.getString("uriImage")
                        val video: String? = document.getString("video")

                        listData.add(Recipes(idDocument!!, calories!!,
                                description!!,
                                idCategory!!,
                                level!!,
                                likes!!,
                                time!!,
                                title!!,
                                uriImage!!,
                                video!!))
                    }
                    mutableData.value = listData

                }
        return mutableData

    }


    fun getOneRecipeData(idRecipe:String): Recipes {

        var mutableData= Recipes()
        FirebaseFirestore.getInstance().collection("recipes").document(idRecipe).get().addOnSuccessListener { result->
            print("ANTOJITOS: ${result.data.toString()}")
            val idDocument: String? = result.id
            val calories: String? = result.getString("calorie")
            val description: String? = result.getString("description")
            val idCategory: String? = result.getString("idCategory")
            val level: String? = result.getString("level")
            val likes: Int? = result.get("likes").toString().toInt()
            val time: String? = result.getString("time")
            val title: String? = result.getString("title")
            val uriImage: String? = result.getString("uriImage")
            val video: String? = result.getString("video")

            val recipes = Recipes(idDocument!!, calories!!,
                    description!!,
                    idCategory!!,
                    level!!,
                    likes!!,
                    time!!,
                    title!!,
                    uriImage!!,
                    video!!)

            mutableData = recipes

        }
        return mutableData


    }



}