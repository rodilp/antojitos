package com.antojitos.pe.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.antojitos.pe.R
import com.antojitos.pe.activity.RecipeActivity
import com.antojitos.pe.model.Recipes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.recipe_items.view.tvi_title_recipe
import kotlinx.android.synthetic.main.toprecipe_items.view.*

class TopRecipeAdapter (private val context: Context) : RecyclerView.Adapter<TopRecipeAdapter.TopRecipeViewHolder>() {


    private var dataList = mutableListOf<Recipes>()

    fun setDataList(data: MutableList<Recipes>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRecipeViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.toprecipe_items, parent, false)
        return TopRecipeViewHolder(view)

    }

    override fun onBindViewHolder(holder: TopRecipeViewHolder, position: Int) {
        val recipes: Recipes = dataList[position]
        holder.bindView(recipes)

    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    inner class TopRecipeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(recipes: Recipes) {
            itemView.tvi_title_recipe2.text = recipes.title
            itemView.tvi_title_descrip2.text = recipes.description
            itemView.tvi_minutes2.text = recipes.time
            Glide.with(context)
                .load(recipes.uriImage)
                .centerCrop()
                .into(itemView.ivi_icon_recipe2)

            itemView.cvi_items_category.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra("idDocument", recipes.idDocument)
                context.startActivity(intent)
            })
        }

    }
}