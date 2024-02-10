package com.graduation.mawruth.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.WindowCompat
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityLoginBinding
import com.graduation.mawruth.utils.RegexConstants


class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    private var txtWatcher: TextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()


    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        textChanger()
    }

    private fun textChanger() {

        txtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = viewBinding.emailInput.text.toString()
                if (!validateEmail(email)) {
                    viewBinding.emailedittext.error = "Enter valid email"
                } else {
                    viewBinding.emailedittext.error = ""
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        viewBinding.emailInput.addTextChangedListener(txtWatcher)
    }

    private fun validateEmail(email: String): Boolean {
        return RegexConstants.emailPattern.matches(email)
    }

}