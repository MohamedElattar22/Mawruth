package com.graduation.mawruth.ui.profile.fragments.editname

import android.content.Context
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
import com.graduation.mawruth.databinding.FragmentEditNameBinding
import com.graduation.mawruth.ui.profile.ProfileActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNameFragment : Fragment() {

    private lateinit var viewBinding: FragmentEditNameBinding
    var user: UserInformationDto? = null
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
        observeLiveData()

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
        }
    }

    fun observeLiveData() {
        viewModel.infoLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Data Updated Successfully", Toast.LENGTH_SHORT)
                    .show()
                (activity as ProfileActivity).initViews()
                findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Error Occurred ,Try again later",
                    Toast.LENGTH_SHORT
                ).show()

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