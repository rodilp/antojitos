package com.antojitos.pe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antojitos.pe.model.Products
import com.antojitos.pe.repository.ProductRepo

class ProductViewModel : ViewModel() {

    private val repo = ProductRepo()

    fun fetchAllProducts(): LiveData<MutableList<Products>> {
        val mutableData= MutableLiveData<MutableList<Products>>()
        repo.getProductsData().observeForever {
            mutableData.value = it
        }
        return  mutableData
    }


    fun fetchTopProducts(): LiveData<MutableList<Products>> {
        val mutableData= MutableLiveData<MutableList<Products>>()
        repo.getTopProductsData().observeForever {
            mutableData.value = it
        }
        return  mutableData
    }
}