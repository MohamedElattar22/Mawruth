package com.graduation.mawruth.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewBinding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
        initViews()

     


    }
    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window , false)
    }


}