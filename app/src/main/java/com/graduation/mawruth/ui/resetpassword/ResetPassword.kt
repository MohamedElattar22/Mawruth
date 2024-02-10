package com.graduation.mawruth.ui.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.graduation.mawruth.databinding.ActivityLoginBinding
import com.graduation.mawruth.databinding.ActivityResetpasswordBinding


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