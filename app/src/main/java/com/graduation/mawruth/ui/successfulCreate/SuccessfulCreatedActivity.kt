package com.graduation.mawruth.ui.successfulCreate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.graduation.mawruth.databinding.ActivitySucessfullCreatedBinding
import com.graduation.mawruth.ui.login.LoginActivity

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
        viewBinding.toRegisterBtn.setOnClickListener {
            navigateToLogin()

        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}