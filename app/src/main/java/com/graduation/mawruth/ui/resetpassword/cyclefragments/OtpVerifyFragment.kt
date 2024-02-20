package com.graduation.mawruth.ui.resetpassword.cyclefragments

import android.app.Dialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.FragmentOtpVerifyBinding
import com.graduation.mawruth.ui.confirmEmail.ConfirmEmailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerifyFragment : Fragment() {
    private lateinit var viewBinding: FragmentOtpVerifyBinding
    private lateinit var viewModel: ConfirmEmailViewModel
    private lateinit var dialog: Dialog
    private var txtWatcher: TextWatcher? = null
    private var cTimer: CountDownTimer? = null
    var result: String? = null
    val email: String? = null
    var selectedPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOtpVerifyBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ConfirmEmailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoadingDialog()
        startTimer()
        initViews()
    }

    private fun setupLoadingDialog() {
        dialog = Dialog(requireActivity())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.loading_dialog, null)
        dialog.setContentView(dialogView)
    }

    private fun initViews() {
        textChanger()
        viewBinding.checkBtn.setOnClickListener {
            verifyOTP()

        }
        subscribeToLiveData()
        viewBinding.logInBtn.setOnClickListener {
            resendOTP()

        }
        subscribeToResend()
    }

    private fun textChanger() {
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
                    result = firstOtp + secondOtp + thirdOtp + fourthOtp

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
        val inputMethodManager = requireActivity()
            .getSystemService(
                INPUT_METHOD_SERVICE
            ) as InputMethodManager
        inputMethodManager.showSoftInput(textInput, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun verifyOTP() {
        Log.d("attar", emailProvider.emailData)
        Log.d("attar", result!!)
        viewModel.verifyEmail(emailProvider.emailData, result!!)
        dialog.show()

    }

    private fun resendOTP() {
        cancelTimer()
        startTimer()
        viewModel.sendOTPToEmail(emailProvider.emailData)
        dialog.show()
    }

    private fun subscribeToLiveData() {
        viewModel.navigate.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getUserByEmail(emailProvider.emailData)
                setNavigation()
                dialog.dismiss()
            }
            viewModel.errorOcc.observe(viewLifecycleOwner) {
                if (it) {
                    Snackbar.make(
                        requireContext(), viewBinding.root, "حدث خطأ ما", Snackbar.LENGTH_SHORT
                    )
                        .show()
                    dialog.dismiss()
                }
            }

        }

    }

    private fun subscribeToResend() {
        viewModel.resendStatus.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(
                    requireContext(),
                    viewBinding.root,
                    "تم اعادة ارسال الرمز",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                dialog.dismiss()
            } else {
                Snackbar.make(
                    requireContext(), viewBinding.root, "حدث خطأ ما", Snackbar.LENGTH_SHORT
                )
                    .show()
                dialog.dismiss()
            }
        }
    }

    private fun setNavigation() {

        findNavController().navigate("resetPassword") {
            anim {
                this.enter = android.R.anim.slide_in_left
            }
            popUpTo(findNavController().graph.id) {
                inclusive = false
            }
        }
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


}