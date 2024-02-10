package com.graduation.mawruth.ui.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.FragmentProfileContentBinding

class ProfileContentFragment : Fragment() {

    private lateinit var viewBinding: FragmentProfileContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentProfileContentBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.editEmail.setOnClickListener {
            findNavController().navigate(R.id.action_profileContentFragment_to_editNameFragment)
        }
        viewBinding.editPassword.setOnClickListener {
            findNavController().navigate(R.id.action_profileContentFragment_to_editPasswordFragment)
        }
    }


}