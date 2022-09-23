package com.antojitos.pe.model

data class Products (var idDocument: String="",
                     var name: String ="",
                     var description: String="",
                     var label: String="",
                     var likes: Int=1,
                     var discount: Double=1.0,
                     var price:Double=1.0,
                     var image: String=""
)