package com.graduation.mawruth.ui.profile.fragments.editpassword

import android.app.Dialog
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
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.FragmentEditPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPasswordFragment : Fragment() {
    lateinit var viewBinding: FragmentEditPasswordBinding
    private lateinit var viewModel: EditPasswordViewModel
    private lateinit var dialog: Dialog
    var isValid = false

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
        viewBinding.newpasstextx.addTextChangedListener(textWatcher)
        viewBinding.newpasstextconfirm.addTextChangedListener(textWatcher)
        viewBinding.oldpasstext.addTextChangedListener(passwordTextWatcher)
        observeLiveData()
        viewBinding.ignoreBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.saveBtn.setOnClickListener {
            if (isValid) {
                dialog.show()
                viewModel.editPassword(
                    viewBinding.newpasstextconfirm.text.toString(),
                    viewBinding.oldpasstext.text.toString()
                )
            }
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
                isValid = false
            } else {
                viewBinding.newpassconfirm.error = null
                viewBinding.newpass.error = null
                isValid = true
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
                isValid = true
            } else {
                viewBinding.oldpass.error = "Invalid password"
                isValid = false
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

    private fun observeLiveData() {
        viewModel.infoLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast
                    .makeText(
                        requireContext(),
                        "Data updated Successfully", Toast.LENGTH_SHORT
                    )
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
}