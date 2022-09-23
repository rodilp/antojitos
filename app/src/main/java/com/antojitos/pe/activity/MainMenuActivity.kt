package com.antojitos.pe.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.antojitos.pe.R
import com.antojitos.pe.model.Device
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore


class MainMenuActivity : AppCompatActivity() {
    private val MY_PREFS_NAME: String = "ConfigPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
        }


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        senInfoDevice()


    }

    private fun senInfoDevice() {

        if(isSendInfo()){
            Log.d("ANTOJITOS: ", "is send ")
        }else{
            val db = FirebaseFirestore.getInstance().collection("devices").document().set(getDeviceInfo())
            senSaveStatusInfo()

        }




    }


    private fun getDeviceInfo(): Device {
        return Device(
                Build.VERSION.INCREMENTAL,
                Build.VERSION.RELEASE,
                Build.VERSION.SDK_INT,
                Build.BOARD,
                Build.BOOTLOADER,
                Build.BRAND,
                Build.CPU_ABI,
                Build.CPU_ABI2,
                Build.DISPLAY,
                Build.FINGERPRINT,
                Build.HARDWARE,
                Build.HOST,
                Build.ID,
                Build.MANUFACTURER,
                Build.MODEL,
                Build.PRODUCT,
                Build.SERIAL,
                Build.TAGS,
                Build.TIME,
                Build.TYPE,
                Build.UNKNOWN,
                Build.USER)
    }

    private fun senSaveStatusInfo() {
        val editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit()
        editor.putBoolean("device", true)
        editor.apply()
    }

    private fun isSendInfo(): Boolean {
        val prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        return prefs.getBoolean("device", false)

    }
}