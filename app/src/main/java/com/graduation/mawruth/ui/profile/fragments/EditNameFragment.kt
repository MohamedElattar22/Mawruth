package com.graduation.mawruth.ui.profile.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.graduation.domain.model.userlogin.UserLoginDto
import com.graduation.mawruth.databinding.FragmentEditNameBinding

class EditNameFragment : Fragment() {

    private lateinit var viewBinding: FragmentEditNameBinding
    var user: UserLoginDto? = null

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


    }

    private fun initViews() {
        val sharedReference = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        sharedReference.getString("userData", null)?.let {
            user = Gson().fromJson(it, UserLoginDto::class.java)
            viewBinding.editName.setText(user?.fullName)
        }
    }


}