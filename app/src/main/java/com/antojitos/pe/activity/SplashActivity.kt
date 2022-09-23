package com.antojitos.pe.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.antojitos.pe.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread.sleep(2000)
        startActivity( Intent(this, OnboardingActivity::class.java))
        finish()
    }
}

