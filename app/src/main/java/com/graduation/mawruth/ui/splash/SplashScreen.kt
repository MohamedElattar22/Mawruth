package com.graduation.mawruth.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityMainBinding
import com.graduation.mawruth.ui.home.HomeActivity
import com.graduation.mawruth.ui.onBoarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()


    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        WindowCompat.setDecorFitsSystemWindows(window, false)
        viewModel.getUserInfo()
        observeLiveData()


    }

    private fun observeLiveData() {
        viewModel.infoLiveData.observe(this) {
            if (it) {
                navigateToHome()
            } else {
                startSplash()
            }
        }
    }


    private fun navigateToHome() {
        animateLogo()
        Handler(Looper.getMainLooper()).postDelayed({
            val home = Intent(this, HomeActivity::class.java)
            startActivity(home)
            finish()
        }, 2700)
    }

    private fun startSplash() {
        animateLogo()
        Handler(Looper.getMainLooper()).postDelayed({
            val startSplash = Intent(this, OnBoardingActivity::class.java)
            startActivity(startSplash)
            finish()
        }, 2700)
    }

    private fun animateLogo() {
        Glide.with(this )
            .load(R.drawable.animated_logo)
            .into(viewBinding.animatedLogo)
    }


}