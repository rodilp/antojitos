package com.antojitos.pe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.antojitos.pe.R
import com.antojitos.pe.model.Ingredients
import kotlinx.android.synthetic.main.ingredients_items.view.*

class IngredientAdapter(private val context: Context) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {


    private var dataList = mutableListOf<Ingredients>()

    fun setDataList(data: MutableList<Ingredients>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.ingredients_items, parent, false)
        return IngredientViewHolder(view)

    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredients: Ingredients = dataList[position]
        holder.bindView(ingredients)

    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    inner class IngredientViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(ingredients: Ingredients) {
            itemView.tvi_ingredient_name.text = ingredients.name


        }

    }

}