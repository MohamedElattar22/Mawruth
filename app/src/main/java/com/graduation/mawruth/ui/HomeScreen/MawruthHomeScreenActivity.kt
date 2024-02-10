package com.graduation.mawruth.ui.HomeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityMawruthHomeScreenBinding

class MawruthHomeScreenActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMawruthHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMawruthHomeScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {

    }
}