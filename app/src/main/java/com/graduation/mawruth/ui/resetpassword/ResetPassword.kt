package com.graduation.mawruth.ui.resetpassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.graduation.mawruth.databinding.ActivityResetpasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPassword : AppCompatActivity() {
    lateinit var viewBinding: ActivityResetpasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityResetpasswordBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }
    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window , false)
    }
}