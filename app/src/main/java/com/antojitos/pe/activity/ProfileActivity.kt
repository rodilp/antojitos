package com.antojitos.pe.activity

import android.content.ContentResolver
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.antojitos.pe.R
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //https://github.com/wsdesignuiux/Android-ui-templates

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
        }

        personalinfo.setVisibility(View.VISIBLE);
        experience.setVisibility(View.GONE);
        review.setVisibility(View.GONE);


        personalinfobtn.setOnClickListener(View.OnClickListener() {
                personalinfo.setVisibility(View.VISIBLE)
                experience.setVisibility(View.GONE)
                review.setVisibility(View.GONE)
                personalinfobtn.setTextColor(resources.getColor(R.color.blue))
                experiencebtn.setTextColor(resources.getColor(R.color.grey))
                reviewbtn.setTextColor(resources.getColor(R.color.grey))
        })

        experiencebtn.setOnClickListener(View.OnClickListener() {
                personalinfo.setVisibility(View.GONE)
                experience.setVisibility(View.VISIBLE)
                review.setVisibility(View.GONE)
                personalinfobtn.setTextColor(resources.getColor(R.color.grey))
                experiencebtn.setTextColor(resources.getColor(R.color.blue))
                reviewbtn.setTextColor(resources.getColor(R.color.grey))
        })

        reviewbtn.setOnClickListener(View.OnClickListener() {
                personalinfo.setVisibility(View.GONE)
                experience.setVisibility(View.GONE)
                review.setVisibility(View.VISIBLE)
                personalinfobtn.setTextColor(resources.getColor(R.color.grey))
                experiencebtn.setTextColor(resources.getColor(R.color.grey))
                reviewbtn.setTextColor(resources.getColor(R.color.blue))

        })

        senMessage.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ConversationActivity::class.java)
            startActivity(intent)
        })
    }
}