package com.graduation.mawruth.ui.confirmemailtoresetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.graduation.mawruth.databinding.ActivityConfirmEmailToResetpassBinding


class confirmemailtoResetPassword : AppCompatActivity() {
    lateinit var viewBinding: ActivityConfirmEmailToResetpassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityConfirmEmailToResetpassBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
        initViews()
    }
    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window , false)
    }


}