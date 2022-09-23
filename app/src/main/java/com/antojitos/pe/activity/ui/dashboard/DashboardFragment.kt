package com.antojitos.pe.activity.ui.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.antojitos.pe.R
import com.antojitos.pe.adapters.*
import com.antojitos.pe.model.Products
import com.antojitos.pe.model.Recipes
import com.antojitos.pe.viewmodel.CategoryViewModel
import com.antojitos.pe.viewmodel.ProductViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*



class DashboardFragment : Fragment() {


    private lateinit var adapterCategory: ProductCategoryAdapter
    private lateinit var adapterProduct: ProductAdapter
    private lateinit var adapterTopProduct: TopProductAdapter
    private var dataList = mutableListOf<Products>()

    private val viewCategoryModel by lazy { ViewModelProvider(this).get(CategoryViewModel::class.java) }
    private val viewProductModel by lazy { ViewModelProvider(this).get(ProductViewModel::class.java) }




    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {



        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)


        adapterCategory = context?.let { ProductCategoryAdapter(it) }!!
        adapterProduct = context?.let { ProductAdapter(it) }!!
        adapterTopProduct = context?.let { TopProductAdapter(it) }!!

        root.rvi_categories_p.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        root.rvi_categories_p.adapter = adapterCategory

        root.rvi_products.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        root.rvi_products.adapter = adapterProduct

        root.rvi_full_tops.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        root.rvi_full_tops.adapter = adapterTopProduct


        activity?.let {
            Glide.with(it)
                .load("https://diabetika.es/modules/homeslider/images/763a76bf65ba5619a4346f8dad6679815f6bc2b0_mochila_ikooki_ES.jpg")
                .centerCrop()
                .into(root.ivi_banner_produc)
        }


        observeData();

        root.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
        })


        return root
    }


    private fun filter(text: String) {
        val filteredList: MutableList<Products?> = ArrayList()
        if (dataList.isNotEmpty()) {
            for (item in dataList) {
                if (item.name.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item)
                }
            }
        }
        try {
            adapterProduct.filterList(filteredList)
        } catch (e: Exception) {
            Log.i("LFIT", "" + e)
        }
    }
    fun observeData() {
        viewCategoryModel.fetchCategoriesProducts().observe(viewLifecycleOwner, Observer {
            adapterCategory.setDataList(it)
            adapterCategory.notifyDataSetChanged()
        })


        viewProductModel.fetchAllProducts().observe(viewLifecycleOwner, {
            adapterProduct.setDataList(it)
            dataList = it
            adapterProduct.notifyDataSetChanged()
        })

        viewProductModel.fetchTopProducts().observe(viewLifecycleOwner, {
            adapterTopProduct.setDataList(it)
            adapterTopProduct.notifyDataSetChanged()
        })


    }
}


