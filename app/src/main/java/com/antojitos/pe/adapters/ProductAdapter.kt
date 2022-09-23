package com.antojitos.pe.adapters


import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.antojitos.pe.R
import com.antojitos.pe.activity.RecipeActivity
import com.antojitos.pe.model.Products
import com.antojitos.pe.model.Recipes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.modal_delete_device.view.*
import kotlinx.android.synthetic.main.products_items.view.*
import kotlinx.android.synthetic.main.recipe_items.view.*
import kotlinx.android.synthetic.main.recipe_items.view.tvi_title_recipe


class ProductAdapter(private val context: Context) : RecyclerView.Adapter<ProductAdapter.RecipeViewHolder>() {


    private var dataList = mutableListOf<Products>()

    private  var dataListFiltered = mutableListOf<Products>()

    fun setDataList(data: MutableList<Products>) {
        dataList = data
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.products_items, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val products: Products = dataList[position]
        holder.bindView(products)

    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }


    inner class RecipeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(products: Products) {


            if(products.discount ==0.0){
                itemView.tvi_discount.visibility= View.GONE
            }else{
                itemView.tvi_discount.text = HtmlCompat.fromHtml("<strike>S/ ${products.discount}</strike>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            }

            itemView.tvi_label.text = products.label
            itemView.tvi_p_name.text = products.name
            itemView.tvi_descrip.text = products.description

            itemView.tvi_price.text = "S/ "+products.price.toString()
            Glide.with(context)
                    .load(products.image)
                    .centerCrop()
                    .into(itemView.ivi_prod_image)

            itemView.but_add_product.setOnClickListener(View.OnClickListener {
                showVideo()
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


    fun showVideo() {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogtheme)

        val root: View = LayoutInflater.from(context).inflate(R.layout.modal_video, null)

        builder.setView(root)

        val alertDialog = builder.create()

        root.btn_delete_band.setOnClickListener(View.OnClickListener {
            alertDialog.dismiss()
        })

        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.show()
    }


    fun filterList(filteredList: MutableList<Products?>) {
        dataListFiltered = filteredList as MutableList<Products>
        dataList = dataListFiltered
        notifyDataSetChanged()
    }

}