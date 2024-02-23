package com.graduation.mawruth.ui.profile.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.FragmentProfileContentBinding
import com.graduation.mawruth.ui.settings.SettingsActivity
import com.graduation.mawruth.ui.splash.SplashScreen

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
        viewBinding.settings.setOnClickListener {
            val intent =Intent(context,SettingsActivity::class.java)
            startActivity(intent)
        }
        viewBinding.logogut.setOnClickListener {
            handelLogOut()
        }
    }

    private fun handelLogOut() {
        val dialog = MaterialAlertDialogBuilder(requireContext()).create()
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.logout_dialog_design, null)
        dialog.setView(dialogView)
        val stay = dialogView.findViewById<MaterialButton>(R.id.stay)
        val logout = dialogView.findViewById<MaterialButton>(R.id.log)
        stay.setOnClickListener {
            dialog.dismiss()
        }
        logout.setOnClickListener {
            val sharedPreferences =
                requireActivity().getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("userData")
            editor.remove("userInfo")
            editor.apply()
            navigateToSplash()
            dialog.dismiss()
        }
        dialog.show()
//        dialog.show()
//        dialog.setPositiveButton(
//            "نعم"
//        ) { dialog, which ->
//            val sharedPreferences =
//                requireActivity().getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.remove("userData")
//            editor.apply()
//            SessionProvider.user = null
//            navigateToSplash()
//            dialog.dismiss()
//        }
//        dialog.setNegativeButton("لا") { dialog, _ ->
//            dialog.dismiss()
//        }
//        dialog.show()
    }

    private fun navigateToSplash() {
        val intent = Intent(requireContext(), SplashScreen::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}