package com.graduation.mawruth.ui.profile.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import com.graduation.mawruth.databinding.FragmentShowProfileImageBinding

class ShowProfileImageFragment : DialogFragment() {
    lateinit var viewBinding: FragmentShowProfileImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        viewBinding = FragmentShowProfileImageBinding.inflate(inflater)
        val bundle = arguments
        bundle?.let {
            val image = bundle.getString("uri")!!.toUri()
            viewBinding.profPic.setImageURI(image)
            viewBinding.saveBtn.visibility = View.VISIBLE
            viewBinding.ignoreBtn.visibility = View.VISIBLE
        }
        return viewBinding.root
    }

}