package com.antojitos.pe.activity

import androidx.appcompat.app.AlertDialog;
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.antojitos.pe.R
import com.antojitos.pe.adapters.IngredientAdapter
import com.antojitos.pe.adapters.NutritionalAdapter
import com.antojitos.pe.adapters.StepsAdapter
import com.antojitos.pe.adapters.TopRecipeAdapter
import com.antojitos.pe.model.Nutritionals
import com.antojitos.pe.viewmodel.IngredientViewModel
import com.antojitos.pe.viewmodel.StepViewModel
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.modal_delete_device.view.*
import kotlinx.android.synthetic.main.modal_delete_device.view.btn_delete_band
import kotlinx.android.synthetic.main.modal_info_nutritional.view.*


class RecipeActivity : AppCompatActivity() {

    private lateinit var ingredientAdapter: IngredientAdapter
    private lateinit var stepsAdapter: StepsAdapter
    private lateinit var adapterNutritionalAdapter: NutritionalAdapter


    private val viewIngredientModel by lazy { ViewModelProviders.of(this).get(IngredientViewModel::class.java) }
    private val viewStepsModel by lazy { ViewModelProviders.of(this).get(StepViewModel::class.java) }
    var idDocument="";




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
        }



        ingredientAdapter = IngredientAdapter(this)
        rvi_ingredients_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvi_ingredients_list.adapter = ingredientAdapter

        stepsAdapter = StepsAdapter(this)
        rvi_steps.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvi_steps.adapter = stepsAdapter



        try {
            val bundle = intent.extras
            idDocument = bundle!!.getString("idDocument").toString()
            loadRecipe(idDocument)
            observer(idDocument)
        } catch (e: Exception) {
            // Must be safe
        }



        ivi_back.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        })

        but_video.setOnClickListener(View.OnClickListener {
            //showVideo()
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)

        })

        tvi_calories.setOnClickListener(View.OnClickListener {
            showInfoNutritional(idDocument)
        })




    }

    private fun loadRecipe(id: String) {
        FirebaseFirestore.getInstance().collection("recipes").document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                tvi_title_recipe.text = document.getString("title")
                tvi_title_descrip.text = document.getString("description")
                tvi_calories.text = HtmlCompat.fromHtml("<a href=\"#\"> ${document.getString("calorie")}</a>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                tvi_time.text = document.getString("time")
                tvi_diff.text = document.getString("level")
                Glide.with(this)
                        .load(document.getString("uriImage"))
                        .centerCrop()
                        .into(circleImageView)
            } else {
                Log.d("ANTOJITOS: ", "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d("ANTOJITOS: ", "get failed with ", exception)
        }

    }

    private fun observer(idRecipe: String){

        viewIngredientModel.fetchIngredientsByRecipe(idRecipe).observe(this, {
            ingredientAdapter.setDataList(it)
            ingredientAdapter.notifyDataSetChanged()
        })

        viewStepsModel.fetchStepsByRecipe(idRecipe).observe(this, {
            stepsAdapter.setDataList(it)
            stepsAdapter.notifyDataSetChanged()
        })


    }

    fun showVideo() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogtheme)

        val root: View = LayoutInflater.from(this).inflate(R.layout.modal_video, null)

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


    fun showInfoNutritional(idRecipe: String) {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogtheme)

        val root: View = LayoutInflater.from(this).inflate(R.layout.modal_info_nutritional, null)



        adapterNutritionalAdapter = NutritionalAdapter(this)
        root.revi_list_nutritionals.layoutManager =   LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        root.revi_list_nutritionals.adapter = adapterNutritionalAdapter



        viewIngredientModel.fetchNutritionalByRecipe(idRecipe).observe(this, {
            var listNut: MutableList<Nutritionals> = mutableListOf()
            listNut =it
            listNut.sortBy { it.order }

            adapterNutritionalAdapter.setDataList(listNut)
            adapterNutritionalAdapter.notifyDataSetChanged()
        })

        builder.setView(root)

        val alertDialog = builder?.create()

        root.btn_delete_band.setOnClickListener(View.OnClickListener {
            alertDialog.dismiss()
        })

        if (alertDialog.window != null) {
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.show()
    }








}