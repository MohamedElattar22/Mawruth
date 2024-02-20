package com.graduation.mawruth.ui.signup


import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivitySignupBinding
import com.graduation.mawruth.ui.confirmEmail.ConfirmEmailActivity
import com.graduation.mawruth.utils.RegexConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel
    private lateinit var dialog: Dialog
    private var txtWatcher: TextWatcher? = null
    var email: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, null)
        dialog.setContentView(dialogView)
        textChanger()
        viewBinding.registerBtn.setOnClickListener {
            viewModel.registerUser()
            dialog.show()
        }
        subscribeToLiveData()
    }

    private fun textChanger() {

        txtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val userName = viewBinding.userInput.text.toString()
                email = viewBinding.emailTxt.text.toString()
                val password = viewBinding.passwordInput.text.toString()
                val passwordConf = viewBinding.passwordConf.text.toString()
                if (password.isNullOrBlank()) {
                    viewBinding.emailContainer.error = "Password is required"
                } else {
                    viewBinding.emailContainer.error = ""
                }
                if (userName.isNullOrBlank()) {
                    viewBinding.userInputLayout.error = "User name is required"
                } else {
                    viewBinding.userInputLayout.error = ""
                }
                if (email.isNullOrBlank()) {
                    viewBinding.emailContainer.error = "Enter valid email"
                } else {
                    viewBinding.emailContainer.error = ""
                }
                if (passwordConf.isNullOrBlank()) {
                    viewBinding.passConfLayout.error = "Password confirmation is required"
                } else {
                    viewBinding.passConfLayout.error = ""
                }
                if (!validateEmail(email)) {
                    viewBinding.emailContainer.error = "Enter valid email"
                } else {
                    viewBinding.emailContainer.error = ""
                }
                if (userName.isNotBlank() && password.isNotBlank() && email.isNotBlank()
                    && password.isNotBlank() && passwordConf.isNotBlank()
                ) {
                    if (password == passwordConf) {

                        viewBinding.registerBtn.isEnabled = true
                        viewBinding.passConfLayout.error = ""
                    } else {
                        viewBinding.passConfLayout.error = "password is not match"
                    }
                }
                if (userName.isBlank() || password.isBlank() || email.isBlank()
                    && password.isBlank() || passwordConf.isBlank()
                    || passwordConf != password
                ) {
                    viewBinding.registerBtn.isEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        viewBinding.emailTxt.addTextChangedListener(txtWatcher)
        viewBinding.passwordConf.addTextChangedListener(txtWatcher)
        viewBinding.userInput.addTextChangedListener(txtWatcher)
        viewBinding.passwordConf.addTextChangedListener(txtWatcher)
    }

    private fun validateEmail(email: String): Boolean {
        return RegexConstants.emailPattern.matches(email)
    }

//    private fun createDialog() {
//        val loadingDialogBinding = layoutInflater.inflate(R.layout.loading_dialog, null)
//        val dialog = MaterialAlertDialogBuilder(this)
//            .setView(loadingDialogBinding)
//        dialog.setBackground(ColorDrawable(Color.TRANSPARENT))
//        dialog.show()
//    }

    private fun subscribeToLiveData() {
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, "Invalid email or username", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        viewModel.openActivity.observe(this) {
            if (it) {
                navigateToVerifyOTP()
                dialog.dismiss()
            }

        }

    }

    private fun navigateToVerifyOTP() {
        val intent = Intent(this, ConfirmEmailActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
        finish()
    }
}