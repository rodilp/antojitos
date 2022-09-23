package com.antojitos.pe.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antojitos.pe.model.Category
import com.antojitos.pe.model.ProductCategories
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CategoryRepo {

    fun getCategoryRecipeData():LiveData<MutableList<Category>>{
        val mutableData= MutableLiveData<MutableList<Category>>()
        FirebaseFirestore.getInstance().collection("categories")
                .orderBy("id", Query.Direction.ASCENDING).get().addOnSuccessListener { result->
            val listData = mutableListOf<Category>()
            for (document  in result){
                val cat : MutableMap<String, Any> = document.data
                //val id:Integer?= document.data["id"] as Int?
                val name: String? = document.getString("name")
                listData.add(Category(cat["id"].toString().toInt() ,name!!))
            }
            mutableData.value = listData
        }
        return mutableData
    }


    fun getCategoryProductData():LiveData<MutableList<ProductCategories>>{
        val mutableData= MutableLiveData<MutableList<ProductCategories>>()
        FirebaseFirestore.getInstance().collection("productCategories")
            .orderBy("id", Query.Direction.ASCENDING).get().addOnSuccessListener { result->
                val listData = mutableListOf<ProductCategories>()
                for (document  in result){
                    val cat : MutableMap<String, Any> = document.data
                    //val id:Integer?= document.data["id"] as Int?
                    val name: String? = document.getString("name")
                    listData.add(ProductCategories(cat["id"].toString().toInt() ,name!!))
                }
                mutableData.value = listData
            }
        return mutableData
    }


}