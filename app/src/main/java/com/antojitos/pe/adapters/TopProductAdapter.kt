package com.antojitos.pe.adapters

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.antojitos.pe.R
import com.antojitos.pe.model.Products
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.modal_delete_device.view.*
import kotlinx.android.synthetic.main.top_products_items.view.*



class TopProductAdapter (private val context: Context) : RecyclerView.Adapter<TopProductAdapter.TopRecipeViewHolder>() {


    private var dataList = mutableListOf<Products>()

    fun setDataList(data: MutableList<Products>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRecipeViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.top_products_items, parent, false)
        return TopRecipeViewHolder(view)

    }

    override fun onBindViewHolder(holder: TopRecipeViewHolder, position: Int) {
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

    inner class TopRecipeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(products: Products) {
            itemView.tvi_title_product.text = products.name
            itemView.tvi_price.text = "S/ "+products.price.toString()
            Glide.with(context)
                .load(products.image)
                .centerCrop()
                .into(itemView.ivi_product)

            itemView.but_add_product.setOnClickListener(View.OnClickListener {
                showVideo()
            })
        }

    }


    fun showVideo() {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogtheme)

        val root: View = LayoutInflater.from(context).inflate(R.layout.modal_video, null)

        builder?.setView(root)

        val alertDialog = builder?.create()

        root.btn_delete_band.setOnClickListener(View.OnClickListener {
            alertDialog?.dismiss()
        })

        if (alertDialog?.getWindow() != null) {
            alertDialog?.getWindow()!!.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog?.show()
    }
}