package com.antojitos.pe.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.antojitos.pe.R
import com.antojitos.pe.activity.RecipeActivity
import com.antojitos.pe.model.Recipes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recipe_items.view.*



class RecipeAdapter(private val context: Context) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    private var dataList = mutableListOf<Recipes>()
    private var dataListFiltered = mutableListOf<Recipes>()

    fun setDataList(data: MutableList<Recipes>) {
        dataList = data
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.recipe_items, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

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


    inner class RecipeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(recipes: Recipes) {
            itemView.tvi_title_recipe.text = recipes.title
            itemView.tvi_title_descrip.text = recipes.description
            itemView.tvi_minutes.text = recipes.time
            Glide.with(context)
                    .load(recipes.uriImage)
                    .centerCrop()
                    .into(itemView.ivi_icon_recipe)

            itemView.cvi_items_category.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra("idDocument", recipes.idDocument)
                context.startActivity(intent)
            })
            //itemView.cla_recipes.setOnClickListener(View.OnClickListener {  })

            /*

            itemView.ivi_icon_recipe.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, "" + recipes.idDocument, Toast.LENGTH_SHORT).show()
            })
            itemView.tvi_title_recipe.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra("idDocument", recipes.idDocument)
                context.startActivity(intent)
            }) */

        }

    }


    fun filterList(filteredList: MutableList<Recipes?>) {
        dataListFiltered = filteredList as MutableList<Recipes>
        dataList = dataListFiltered
        notifyDataSetChanged()
    }


}