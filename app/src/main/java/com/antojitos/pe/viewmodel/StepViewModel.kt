package com.antojitos.pe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antojitos.pe.model.Steps
import com.antojitos.pe.repository.StepsRepo

class StepViewModel  : ViewModel() {
    private val repo = StepsRepo()

    fun fetchStepsByRecipe(idRecipe:String ): LiveData<MutableList<Steps>> {
        val mutableData= MutableLiveData<MutableList<Steps>>()
        repo.getStepsData(idRecipe).observeForever {
            mutableData.value = it
        }
        return  mutableData
    }
}