package com.graduation.mawruth.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityMainBinding
import com.graduation.mawruth.ui.onBoarding.OnBoardingActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()


    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window , false)
        animateLogo()
        startSplash()
    }

    private fun startSplash() {
        Handler(Looper.getMainLooper()).postDelayed( {
            val startSplash = Intent(this , OnBoardingActivity::class.java )
            startActivity(startSplash)
            finish()
        },2500)
    }

    private fun animateLogo() {
        Glide.with(this )
            .load(R.drawable.animated_logo)
            .into(viewBinding.animatedLogo)
    }


}