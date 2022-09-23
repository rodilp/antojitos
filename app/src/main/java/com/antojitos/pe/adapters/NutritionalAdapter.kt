package com.antojitos.pe.adapters

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.antojitos.pe.R
import com.antojitos.pe.model.Category
import com.antojitos.pe.model.Nutritionals
import kotlinx.android.synthetic.main.category_items.view.*
import kotlinx.android.synthetic.main.category_items.view.tvi_name_cat
import kotlinx.android.synthetic.main.items_nutritional.view.*

class NutritionalAdapter(private val context: Context) : RecyclerView.Adapter<NutritionalAdapter.CategoryViewHolder>() {


    private var dataList = mutableListOf<Nutritionals>()

    fun setDataList(data: MutableList<Nutritionals>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.items_nutritional, parent, false)
        return CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val nutritionals: Nutritionals = dataList[position]
        holder.bindView(nutritionals)

    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    inner class CategoryViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(nutritionals: Nutritionals) {
            var str = nutritionals.name

            val name1 = str.substring(0, str.indexOf("-"))
            val name2 = str.substring(str.indexOf("-") + 1, str.length)
            itemView.tvi_name1.text = name1
            itemView.tvi_name2.text = name2

            /*
            itemView.tvi_name_cat.setOnClickListener(View.OnClickListener {
                itemView.cla_back_category.setBackgroundColor(ContextCompat.getColor(context, R.color.primary_c))
                itemView.tvi_name_cat.setTextColor(ContextCompat.getColor(context, R.color.white))


            }) */

        }

    }

}