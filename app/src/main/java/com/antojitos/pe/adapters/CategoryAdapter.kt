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
import kotlinx.android.synthetic.main.category_items.view.*

class CategoryAdapter(private val context: Context) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    private var dataList = mutableListOf<Category>()

    fun setDataList(data: MutableList<Category>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.category_items, parent, false)
        return CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = dataList[position]
        holder.bindView(category)

    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    inner class CategoryViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(category: Category) {
            itemView.tvi_name_cat.text = category.name

            /*
            itemView.tvi_name_cat.setOnClickListener(View.OnClickListener {
                itemView.cla_back_category.setBackgroundColor(ContextCompat.getColor(context, R.color.primary_c))
                itemView.tvi_name_cat.setTextColor(ContextCompat.getColor(context, R.color.white))


            }) */

        }

    }

}