package com.graduation.mawruth.ui.profile.fragments.editname

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
import com.graduation.mawruth.databinding.FragmentEditNameBinding
import com.graduation.mawruth.ui.profile.ProfileActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNameFragment : Fragment() {

    private lateinit var viewBinding: FragmentEditNameBinding
    var user: UserInformationDto? = null
    private lateinit var dialog: Dialog
    lateinit var viewModel: EditNameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditNameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentEditNameBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setDialog()
        observeLiveData()

    }

    private fun setDialog() {
        dialog = Dialog(requireActivity())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.loading_dialog, null)
        dialog.setContentView(dialogView)
    }

    private fun initViews() {
        val sharedReference = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        sharedReference.getString("userInfo", null)?.let {
            user = Gson().fromJson(it, UserInformationDto::class.java)
            viewBinding.editName.setText(user?.fullName)
        }
        viewBinding.ignoreBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.editName.addTextChangedListener(textWatcher)
        viewBinding.saveBtn.setOnClickListener {
            viewModel.editName(viewBinding.editName.text.toString())
            dialog.show()
        }
    }

    private fun observeLiveData() {
        viewModel.infoLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Data Updated Successfully", Toast.LENGTH_SHORT)
                    .show()
                (activity as ProfileActivity).initViews()
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
        viewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    "Error Occurred ,Try again later",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            viewBinding.saveBtn.isEnabled = false
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            viewBinding.saveBtn.isEnabled =
                viewBinding.editName.text.toString() != user?.fullName.toString()

        }

        override fun afterTextChanged(s: Editable?) {
        }

    }


}