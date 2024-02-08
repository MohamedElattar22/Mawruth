package com.graduation.mawruth.ui.signup


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.WindowCompat
import com.graduation.mawruth.databinding.ActivitySignupBinding
import com.graduation.mawruth.utils.RegexConstants

class SignupActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignupBinding
    private var txtWatcher: TextWatcher? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
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
                val email = viewBinding.emailTxt.text.toString()
                if (!validateEmail(email)) {
                    viewBinding.emailContainer.error = "Enter valid email"
                } else {
                    viewBinding.emailContainer.error = ""
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        viewBinding.emailTxt.addTextChangedListener(txtWatcher)
    }

    private fun validateEmail(email: String): Boolean {
        return RegexConstants.emailPattern.matches(email)
    }
}