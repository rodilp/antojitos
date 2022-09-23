package com.antojitos.pe.activity.ui.home


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.antojitos.pe.R
import com.antojitos.pe.adapters.CategoryAdapter
import com.antojitos.pe.adapters.RecipeAdapter
import com.antojitos.pe.adapters.TopRecipeAdapter
import com.antojitos.pe.model.Recipes
import com.antojitos.pe.viewmodel.CategoryViewModel
import com.antojitos.pe.viewmodel.RecipeViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.modal_delete_device.view.*


class HomeFragment : Fragment() {

    private val MY_PREFS_NAME: String = "ConfigPref"

    private lateinit var adapterCategory: CategoryAdapter
    private lateinit var adapterRecipe: RecipeAdapter
    private lateinit var adapterTopRecipe: TopRecipeAdapter

    private var dataList = mutableListOf<Recipes>()

    private val viewCategoryModel by lazy { ViewModelProvider(this).get(CategoryViewModel::class.java) }
    private val viewRecipeModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)

        adapterCategory = context?.let { CategoryAdapter(it) }!!
        adapterRecipe = context?.let { RecipeAdapter(it) }!!
        adapterTopRecipe = context?.let { TopRecipeAdapter(it) }!!

        root.rvi_categories.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        root.rvi_categories.adapter = adapterCategory

        root.rvi_recipes.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        root.rvi_recipes.adapter = adapterRecipe

        root.rvi_top_recipes.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        root.rvi_top_recipes.adapter = adapterTopRecipe

        activity?.let {
            Glide.with(it)
                .load("https://diabetika.es/modules/homeslider/images/f25cd91fa77ae5032ab0300ada2c919c1ccf3192_slider_lizza_ES.png")
                .centerCrop()
                .into(root.ivi_banner_recipe)
        }

        observeData()

        root.editTextTextPersonName1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
        })


        if(isSendInfo()){
            Log.d("ANTOJITOS: ", "is send ")
        }else{
            deleteDevice()
        }
        return root
    }

    private fun filter(text: String) {
        val filteredList: MutableList<Recipes?> = ArrayList()
        if (!dataList.isEmpty()) {
            for (item in dataList) {
                if (item.title.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item)
                }
            }
        }
        try {
            adapterRecipe.filterList(filteredList)
        } catch (e: Exception) {
            Log.i("LFIT", "" + e)
        }
    }

    fun observeData() {
        viewCategoryModel.fetchCategoriesRecipe().observe(viewLifecycleOwner, Observer {
            adapterCategory.setDataList(it)
            adapterCategory.notifyDataSetChanged()
        })


        viewRecipeModel.fetchRecipes().observe(viewLifecycleOwner, {
            dataList = it
            adapterRecipe.setDataList(it)
            adapterRecipe.notifyDataSetChanged()
        })

        viewRecipeModel.fetchTopRecipes().observe(viewLifecycleOwner, {
            adapterTopRecipe.setDataList(it)
            adapterTopRecipe.notifyDataSetChanged()
        })


    }


    fun deleteDevice() {
        val builder = context?.let { AlertDialog.Builder(it, R.style.AlertDialogtheme) }

        val root: View = LayoutInflater.from(context).inflate(R.layout.modal_delete_device, null)

        builder?.setView(root)

        val alertDialog = builder?.create()

        root.btn_delete_band.setOnClickListener(View.OnClickListener {
            senSaveStatusInfo()
            alertDialog?.dismiss()
        })

        if (alertDialog?.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog?.show()
    }


    private fun senSaveStatusInfo() {
        val editor = context?.getSharedPreferences(MY_PREFS_NAME, AppCompatActivity.MODE_PRIVATE)?.edit()
        editor?.putBoolean("welcome", true)
        editor?.apply()
    }

    private fun isSendInfo(): Boolean {
        val prefs = context?.getSharedPreferences(MY_PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        return prefs!!.getBoolean("welcome", false)
    }
}