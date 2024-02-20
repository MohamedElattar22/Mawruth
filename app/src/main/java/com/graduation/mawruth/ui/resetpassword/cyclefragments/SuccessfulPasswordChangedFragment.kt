package com.graduation.mawruth.ui.resetpassword.cyclefragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.graduation.mawruth.databinding.FragmentSuccessfulPasswordChangedBinding
import com.graduation.mawruth.ui.login.LoginActivity


class SuccessfulPasswordChangedFragment : Fragment() {
    private lateinit var viewBinding: FragmentSuccessfulPasswordChangedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSuccessfulPasswordChangedBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        successAndLogin()
    }

    private fun successAndLogin() {
        viewBinding.toRegisterBtn.setOnClickListener {
            findNavController().popBackStack("successCreated", true)
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}