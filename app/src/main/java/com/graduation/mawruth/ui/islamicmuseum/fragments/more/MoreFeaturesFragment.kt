package com.graduation.mawruth.ui.islamicmuseum.fragments.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import com.github.drjacky.imagepicker.ImagePicker.Companion.with
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.graduation.mawruth.databinding.FragmentMoreFeaturesBinding
import com.graduation.mawruth.ui.ClassifyingActivity


class MoreFeaturesFragment : BottomSheetDialogFragment() {

    private lateinit var viewBinding: FragmentMoreFeaturesBinding
    private lateinit var uri: Uri
    private var launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data?.data != null) {
            uri = it.data?.data!!
            val intent = Intent(requireContext(), ClassifyingActivity::class.java)
            Log.e("enter", "enter")
            intent.putExtra("uri", uri)
            startActivity(intent)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMoreFeaturesBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.materialCardView2.setOnClickListener {

            val intent = with(requireActivity())
                .cameraOnly()
                .createIntent()
            launcher.launch(intent)
        }
        viewBinding.feature3.setOnClickListener {
            startUnityActivity()
        }
    }

    private fun startUnityActivity() {
        val uri = Uri.parse("myapp://unity")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}