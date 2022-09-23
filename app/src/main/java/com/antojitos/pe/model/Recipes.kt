package com.antojitos.pe.model

data class Recipes(var idDocument: String="",
                   var calorie: String ="",
                   var description: String="",
                   var idCategory: String="",
                   var level: String="",
                   var likes: Int=1,
                   var time: String="",
                   var title: String="",
                   var uriImage: String="",
                   var video: String=""

)