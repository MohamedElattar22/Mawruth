package com.graduation.mawruth.ui.confirmEmail


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.textfield.TextInputEditText
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityConfirmEmailBinding
import com.graduation.mawruth.ui.successfulCreate.SuccessfulCreatedActivity


class ConfirmEmailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityConfirmEmailBinding
    var cTimer: CountDownTimer? = null
    val inputManager: InputMethodManager? = null
    var txtWatcher: TextWatcher? = null
    var selectedPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityConfirmEmailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        startTimer()
        textChanger()
        viewBinding.checkBtn.setOnClickListener {
            navigateToSuccess()
        }

    }

    private fun navigateToSuccess() {
        val intent = Intent(this, SuccessfulCreatedActivity::class.java)
        startActivity(intent)
    }

    private fun startTimer() {
        cTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                viewBinding.timerTV.text = "0:" + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                cancelTimer()
            }
        }
        (cTimer as CountDownTimer).start()
    }

    fun cancelTimer() {
        if (cTimer != null) cTimer!!.cancel()
    }

    fun textChanger() {
        showKeyBoard(viewBinding.otp1)
        txtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val firstOtp = viewBinding.otp1.text.toString()
                val secondOtp = viewBinding.otp2.text.toString()
                val thirdOtp = viewBinding.otp3.text.toString()
                val fourthOtp = viewBinding.otp4.text.toString()
                if (firstOtp.isNotBlank() && secondOtp.isNotBlank()
                    && thirdOtp.isNotBlank() && fourthOtp.isNotBlank()
                ) {
                    viewBinding.checkBtn.isEnabled = true
                    val color = getColor(R.color.mainBtn)
                    viewBinding.checkBtn.setBackgroundColor(color)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length!! > 0) {
                    if (selectedPosition == 0) {
                        selectedPosition = 1
                        showKeyBoard(viewBinding.otp2)
                    } else if (selectedPosition == 1) {
                        selectedPosition = 2
                        showKeyBoard(viewBinding.otp3)
                    } else if (selectedPosition == 2) {
                        selectedPosition = 3
                        showKeyBoard(viewBinding.otp4)
                    }
                }
            }

        }
        viewBinding.otp1.addTextChangedListener(txtWatcher)
        viewBinding.otp2.addTextChangedListener(txtWatcher)
        viewBinding.otp3.addTextChangedListener(txtWatcher)
        viewBinding.otp4.addTextChangedListener(txtWatcher)
    }

    private fun showKeyBoard(textInput: TextInputEditText) {
        textInput.requestFocus()
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(textInput, InputMethodManager.SHOW_IMPLICIT)
    }

}