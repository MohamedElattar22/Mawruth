package com.graduation.mawruth.ui.profile.fragments.editpassword

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.FragmentEditPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPasswordFragment : Fragment() {
    lateinit var viewBinding: FragmentEditPasswordBinding
    private lateinit var viewModel: EditPasswordViewModel
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditPasswordViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentEditPasswordBinding.inflate(inflater)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialog()
        requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
            .getString("userInfo", null)?.let {
                val user = Gson().fromJson(it, UserInformationDto::class.java)
                viewBinding.newpasstextx.addTextChangedListener(textWatcher)
                viewBinding.newpasstextconfirm.addTextChangedListener(textWatcher)
                viewBinding.oldpasstext.addTextChangedListener(passwordTextWatcher)
                viewBinding.saveBtn.setOnClickListener {

                    if (handelTextError(user)) {
                        dialog.show()
                        viewModel.editPassword(viewBinding.newpasstextx.text.toString().trim())
                    }
                }
            }
        observeLiveData()
        viewBinding.ignoreBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setDialog() {
        dialog = Dialog(requireActivity())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.loading_dialog, null)
        dialog.setContentView(dialogView)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (viewBinding.newpasstextconfirm.text.toString().trim()
                    .isBlank() || viewBinding.newpasstextx.text.toString().trim()
                    .isBlank() || viewBinding.newpasstextconfirm.text.toString()
                    .trim() != viewBinding.newpasstextx.text.toString().trim()
            ) {
                viewBinding.newpassconfirm.error = "Password is not match"
                viewBinding.newpass.error = "Password is not match"
            } else {
                viewBinding.newpassconfirm.error = null
                viewBinding.newpass.error = null
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }
    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (viewBinding.oldpasstext.text.toString().trim().isNotBlank()) {
                viewBinding.oldpass.error = ""
            } else {
                viewBinding.oldpass.error = "Invalid password"

            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

    private fun observeLiveData() {
        viewModel.infoLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Data updated Successfully", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
                findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Error Occurred ,Try again later",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()

            }
        }
    }

    private fun handelTextError(user: UserInformationDto): Boolean {
        var isInvalid = false
        if (user.password.toString() != viewBinding.oldpasstext.text.toString().trim()) {
            viewBinding.oldpass.error = "Invalid password"
        } else {
            viewBinding.oldpass.error = null
            isInvalid = true
        }
        if (viewBinding.newpasstextx.text.toString().trim()
                .isBlank() || viewBinding.newpasstextconfirm.text.toString().trim()
                .isBlank()
        ) {
            viewBinding.newpassconfirm.error = "Password is not match"
            viewBinding.newpass.error = "Password is not match"
        } else {
            viewBinding.newpassconfirm.error = null
            viewBinding.newpass.error = null
            isInvalid = true
        }


        return isInvalid
    }

}