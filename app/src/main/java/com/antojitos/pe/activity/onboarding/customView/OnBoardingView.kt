package com.antojitos.pe.activity.onboarding.customView

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.antojitos.pe.R
import com.antojitos.pe.activity.MainMenuActivity
import com.antojitos.pe.domain.OnBoardingPrefManager
import com.antojitos.pe.model.OnBoardingPage
import com.antojitos.pe.activity.onboarding.OnBoardingPagerAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.onboarding_view.view.*
import setParallaxTransformation

class OnBoardingView @JvmOverloads


constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val numberOfPages by lazy { OnBoardingPage.values().size }
    private val prefManager: OnBoardingPrefManager
    private val MY_PREFS_NAME: String = "ConfigPref"

    init {
        if(isSendInfo()){
            Log.d("ANTOJITOS: ", "is send ")
            context.startActivity(Intent(context, MainMenuActivity::class.java))
            (context as Activity).finish()
        }

        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)
        setUpSlider(view)
        addingButtonsClickListeners()
        prefManager = OnBoardingPrefManager(view.context)
    }

    private fun setUpSlider(view: View) {
        with(slider) {
            adapter = OnBoardingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }

            addSlideChangeListener()

            val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            wormDotsIndicator.setViewPager2(this)
        }
    }


    private fun addSlideChangeListener() {

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }

    private fun addingButtonsClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide() }
        skipBtn.setOnClickListener {
            navigateToBackSlide()
        }
        startBtn.setOnClickListener {
            senSaveStatusInfo()
            context.startActivity(Intent(context, MainMenuActivity::class.java))
            (context as Activity).finish()

        }
    }

    private fun setFirstTimeLaunchToFalse() {
        prefManager.isFirstTimeLaunch = false
    }

    private fun navigateToNextSlide() {
        val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }

    private fun navigateToBackSlide() {
        val nextSlidePos: Int = slider?.currentItem?.minus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }



    private fun senSaveStatusInfo() {
        val editor = context.getSharedPreferences(MY_PREFS_NAME, AppCompatActivity.MODE_PRIVATE).edit()
        editor.putBoolean("onboarding", true)
        editor.apply()
    }

    private fun isSendInfo(): Boolean {
        val prefs = context.getSharedPreferences(MY_PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        return prefs.getBoolean("onboarding", false)

    }


}