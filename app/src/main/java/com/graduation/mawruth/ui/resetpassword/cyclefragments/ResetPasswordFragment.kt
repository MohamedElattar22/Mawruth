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
import com.graduation.mawruth.databinding.FragmentResetPasswordBinding
import com.graduation.mawruth.ui.resetpassword.ResetPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private lateinit var viewBinding: FragmentResetPasswordBinding
    private lateinit var viewModel: ResetPasswordViewModel
    private var txtWatcher: TextWatcher? = null
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentResetPasswordBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ResetPasswordViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireActivity())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.loading_dialog, null)
        dialog.setContentView(dialogView)
        initViews()

    }

    private fun initViews() {
        viewBinding.passwordsaveBtn.setOnClickListener {
            resetPassword()
        }
        subscribeToLiveData()
    }

    private fun setNavigation() {
        findNavController().navigate("successCreated") {
            anim {
                this.enter = android.R.anim.slide_in_left
            }
            popUpTo(findNavController().graph.id) {
                inclusive = false
            }
        }
    }

    private fun resetPassword() {
        val password = viewBinding.password.text.toString()
        val email = emailProvider.emailData
        viewModel.editPassword(email, password)
        dialog.show()
    }

    private fun subscribeToLiveData() {
        viewModel.infoLiveData.observe(viewLifecycleOwner) {
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

    private fun textChanger() {

        txtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = viewBinding.password.text.toString()
                val passwordConfirm = viewBinding.passwordConf.text.toString()
                if (password.isBlank()) {
                    viewBinding.passwordsaveBtn.isEnabled = false
                    viewBinding.newPassword.error = "Password shouldn't be empty !"
                    viewBinding.confirmPassword.error = "Password shouldn't be empty !"

                } else {
                    viewBinding.newPassword.error = ""
                    viewBinding.passwordConf.isEnabled = true

                }
                if (password != passwordConfirm) {
                    viewBinding.passwordsaveBtn.isEnabled = false
                    viewBinding.confirmPassword.error = "Password confirmation is wrong ! "
                } else {
                    viewBinding.confirmPassword.error = ""
                    viewBinding.passwordConf.isEnabled = true

                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        viewBinding.password.addTextChangedListener(txtWatcher)
        viewBinding.passwordConf.addTextChangedListener(txtWatcher)

    }


}