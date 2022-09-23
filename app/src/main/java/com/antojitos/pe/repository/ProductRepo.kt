package com.antojitos.pe.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antojitos.pe.model.Products
import com.antojitos.pe.model.Recipes
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ProductRepo {

    fun getProductsData(): LiveData<MutableList<Products>> {
        val mutableData = MutableLiveData<MutableList<Products>>()
        FirebaseFirestore.getInstance().collection("products").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Products>()
            for (document in result) {
                //val cat : MutableMap<String, Any> = document.data
                val idDocument: String? = document.id
                val label: String? = document.getString("label")
                val likes: Int? = document.get("likes").toString().toInt()
                val discount: Double? = document.get("discount").toString().toDouble()
                val price: Double? = document.get("price").toString().toDouble()
                val name: String? = document.getString("name")
                val description: String? = document.getString("description")
                val image: String? = document.getString("image")

                listData.add(
                    Products(idDocument!!, name!!,
                    description!!,
                    label!!,
                    likes!!,
                    discount!!,
                    price!!,
                    image!!)
                )
            }
            mutableData.value = listData
        }
        return mutableData
    }

    fun getTopProductsData(): LiveData<MutableList<Products>> {
        val mutableData = MutableLiveData<MutableList<Products>>()
        FirebaseFirestore.getInstance().collection("products")
            .whereGreaterThan("likes", 30)
            .orderBy("likes", Query.Direction.DESCENDING).get().addOnSuccessListener { result ->

                val listData = mutableListOf<Products>()
                for (document in result) {

                    val idDocument: String? = document.id
                    val label: String? = document.getString("label")
                    val likes: Int? = document.get("likes").toString().toInt()
                    val discount: Double? = document.get("discount").toString().toDouble()
                    val price: Double? = document.get("price").toString().toDouble()
                    val name: String? = document.getString("name")
                    val description: String? = document.getString("description")
                    val image: String? = document.getString("image")

                    listData.add(
                        Products(idDocument!!, name!!,
                            description!!,
                            label!!,
                            likes!!,
                            discount!!,
                            price!!,
                            image!!)
                    )
                }
                mutableData.value = listData

            }
        return mutableData

    }

}