package com.graduation.mawruth.ui.successfulCreate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivitySucessfullCreatedBinding

class SuccessfulCreatedActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySucessfullCreatedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySucessfullCreatedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}