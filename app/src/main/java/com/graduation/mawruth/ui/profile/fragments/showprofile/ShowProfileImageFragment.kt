package com.graduation.mawruth.ui.profile.fragments.showprofile

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.FragmentShowProfileImageBinding
import com.graduation.mawruth.ui.profile.ProfileActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class ShowProfileImageFragment : DialogFragment() {
    lateinit var viewBinding: FragmentShowProfileImageBinding
    lateinit var viewModel: ShowProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ShowProfileViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        viewBinding = FragmentShowProfileImageBinding.inflate(inflater)
        return viewBinding.root
    }

    @SuppressLint("Recycle")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let {
            val out = it.getInt("out")
            val image = it.getString("uri")
            when (out) {
                0 -> {
                    Glide.with(requireActivity()).load(image).placeholder(R.drawable.person)
                        .into(viewBinding.dialogProfilePic)
                    viewBinding.saveBtn.visibility = View.INVISIBLE
                    viewBinding.ignoreBtn.visibility = View.INVISIBLE
                }

                1 -> {
                    Glide.with(requireActivity()).load(image).placeholder(R.drawable.person)
                        .into(viewBinding.dialogProfilePic)
                    viewBinding.saveBtn.visibility = View.VISIBLE
                    viewBinding.ignoreBtn.visibility = View.VISIBLE
                }
            }
            viewBinding.saveBtn.setOnClickListener {
                val fileDir = requireContext().filesDir
                val file = File(fileDir, "image.jpg")
                val inputStream = requireContext().contentResolver.openInputStream(image!!.toUri())
                val outPutStream = FileOutputStream(file)
                inputStream!!.copyTo(outPutStream)
                viewModel.editUserPhoto(file)
            }
            observeLiveData()
        }
        viewBinding.ignoreBtn.setOnClickListener {
            dismiss()
        }
    }

    private fun observeLiveData() {
        viewModel.infoLiveData.observe(viewLifecycleOwner) {
            if (it) {
                (requireActivity() as ProfileActivity).initViews()
                dialog?.dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Error Occurred ,Try again later",
                    Toast.LENGTH_SHORT
                ).show()
                dialog?.dismiss()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                viewBinding.loading.visibility = View.VISIBLE
                viewBinding.dialogProfilePic.alpha = 0.8f
            }
        }
    }

}