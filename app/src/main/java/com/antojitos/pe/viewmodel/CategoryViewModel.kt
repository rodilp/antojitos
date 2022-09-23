package com.antojitos.pe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antojitos.pe.model.Category
import com.antojitos.pe.model.ProductCategories
import com.antojitos.pe.repository.CategoryRepo

class CategoryViewModel: ViewModel() {
    private val repo = CategoryRepo()

    fun fetchCategoriesRecipe():LiveData<MutableList<Category>>{
        val mutableData=MutableLiveData<MutableList<Category>>()
        repo.getCategoryRecipeData().observeForever {
            mutableData.value = it
        }
        return  mutableData
    }


    fun fetchCategoriesProducts():LiveData<MutableList<ProductCategories>>{
        val mutableData=MutableLiveData<MutableList<ProductCategories>>()
        repo.getCategoryProductData().observeForever {
            mutableData.value = it
        }
        return  mutableData
    }
}