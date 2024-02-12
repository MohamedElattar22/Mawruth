package com.graduation.mawruth.ui.getstarted


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.graduation.mawruth.databinding.ActivityGetStartedBinding
import com.graduation.mawruth.ui.home.HomeActivity
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
        guestMode()
    }

    private fun guestMode() {
        viewBinding.guestBtn.setOnClickListener {
            val login = Intent(this, HomeActivity::class.java)
            startActivity(login)

        }
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