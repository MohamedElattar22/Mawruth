package com.graduation.mawruth.ui.profile.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.graduation.mawruth.databinding.FragmentShowProfileImageBinding

class ShowProfileImageFragment : DialogFragment() {
    lateinit var viewBinding: FragmentShowProfileImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        viewBinding = FragmentShowProfileImageBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let {
            val image = bundle.getString("uri")
            Log.e("photo", image.toString())
            Glide.with(requireActivity()).load(image?.toUri()).into(viewBinding.dialogProfilePic)
//            viewBinding.saveBtn.visibility = View.VISIBLE
//            viewBinding.ignoreBtn.visibility = View.VISIBLE
        }

    }

}