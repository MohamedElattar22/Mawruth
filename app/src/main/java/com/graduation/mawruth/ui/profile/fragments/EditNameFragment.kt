package com.graduation.mawruth.ui.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.graduation.mawruth.databinding.FragmentEditNameBinding
import com.graduation.mawruth.utils.SessionProvider

class EditNameFragment : Fragment() {

    private lateinit var viewBinding: FragmentEditNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentEditNameBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.editName.setText(SessionProvider.user?.fullName)

    }


}