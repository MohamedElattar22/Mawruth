package com.graduation.mawruth.ui.resetpassword.cyclefragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.FragmentEnterEmailBinding
import com.graduation.mawruth.utils.RegexConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterEmailFragment : Fragment() {
    private lateinit var viewBinding: FragmentEnterEmailBinding
    private lateinit var viewModel: EnterEmailViewModel
    private var txtWatcher: TextWatcher? = null
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentEnterEmailBinding.inflate(inflater)

        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EnterEmailViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireActivity())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.loading_dialog, viewBinding.root)
        dialog.setContentView(dialogView)
        initViews()


    }

    private fun initViews() {

        textChanger()
        viewBinding.sendOTPBtn.setOnClickListener {
            sendOTPToUser()
        }
        subscribe()

    }

    private fun setNavigation() {


        findNavController().navigate("verifyOTP") {
            anim {
                this.enter = android.R.anim.slide_in_left
            }
            popUpTo(findNavController().graph.id) {
                inclusive = false
            }

        }

    }

    private fun sendOTPToUser() {
        val email = viewBinding.userInput.text.toString()
        emailProvider.emailData = email
        viewModel.sendOTPToEmail(email)
        dialog.show()


    }

    private fun textChanger() {

        txtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = viewBinding.userInput.text.toString()
                if (email.isBlank() || !validateEmail(email)) {
                    viewBinding.sendOTPBtn.isEnabled = false
                    viewBinding.newPassword.error = "Enter a valid email"

                } else {
                    viewBinding.newPassword.error = ""
                    viewBinding.sendOTPBtn.isEnabled = true

                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        viewBinding.userInput.addTextChangedListener(txtWatcher)

    }

    private fun validateEmail(email: String): Boolean {
        return RegexConstants.emailPattern.matches(email)
    }

    private fun subscribe() {
        viewModel.data.observe(viewLifecycleOwner) {
            if (it) {
                setNavigation()
                dialog.dismiss()
            } else {
                Snackbar.make(
                    requireContext(), viewBinding.root, "حدث خطأ ما", Snackbar.LENGTH_SHORT
                ).show()
                dialog.dismiss()

            }

        }
    }

}