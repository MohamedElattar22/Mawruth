package com.graduation.mawruth.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.databinding.ActivityLoginBinding
import com.graduation.mawruth.ui.home.HomeActivity
import com.graduation.mawruth.utils.RegexConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    private var emailTxtWatcher: TextWatcher? = null
    private var passwordTxtWatcher: TextWatcher? = null
    private lateinit var viewModel: LoginViewModel
    private var emailValid = false
    private var passwordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
        observeLiveData()
        textChanger()
        viewBinding.logInBtn.setOnClickListener {
            if (emailValid && passwordValid) {
                viewModel.login(
                    viewBinding.emailInput.text.toString(),
                    viewBinding.passwordInput.text.toString()
                )
            } else {
                Toast.makeText(this, "InValid Email or Password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun observeLiveData() {
        viewModel.userLiveData.observe(this)
        {
            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("userData", it)
            editor.apply()
            goToHomeActivity()
        }
        viewModel.errorLiveData.observe(this)
        {
            Toast.makeText(this, "error occurred please try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToHomeActivity() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }


    private fun textChanger() {
        var emailIsValid = false
        var passwordIsValid = false
        emailTxtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = viewBinding.emailInput.text.toString()
                if (!validateEmail(email)) {
                    viewBinding.emailedittext.error = "Enter valid email"
                    emailIsValid = false
                } else {
                    viewBinding.emailedittext.error = ""
                    emailIsValid = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                emailValid = emailIsValid
            }

        }
        viewBinding.emailInput.addTextChangedListener(emailTxtWatcher)

        passwordTxtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (viewBinding.passwordInput.text.isNullOrBlank()) {
                    viewBinding.passwordedittext.error = "Enter valid password"
                    passwordIsValid = false
                } else {
                    viewBinding.passwordedittext.error = ""
                    passwordIsValid = true
                }
            }


            override fun afterTextChanged(s: Editable?) {
                passwordValid = passwordIsValid
            }

        }
        viewBinding.passwordInput.addTextChangedListener(passwordTxtWatcher)
    }

    private fun validateEmail(email: String): Boolean {
        return RegexConstants.emailPattern.matches(email)
    }

}