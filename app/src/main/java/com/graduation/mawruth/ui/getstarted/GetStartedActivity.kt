package com.graduation.mawruth.ui.getstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.WindowCompat
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityGetStartedBinding
import com.graduation.mawruth.ui.login.LoginActivity
import com.graduation.mawruth.ui.signup.SignupActivity

class GetStartedActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window , false)
        navigateToSignUp()
        navigateToLogin()
//        guestMode()
    }

    private fun guestMode() {

    }

    private fun navigateToLogin() {
        viewBinding.logInBtn.setOnClickListener {
            val login = Intent(this , LoginActivity::class.java)
            startActivity(login)
        }
    }

    private fun navigateToSignUp() {
        viewBinding.toRegisterBtn.setOnClickListener {
            val signUp = Intent(this , SignupActivity::class.java)
            startActivity(signUp)
        }
    }
}