package com.graduation.mawruth.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.graduation.mawruth.databinding.ActivityProfileBinding
import com.graduation.mawruth.ui.profile.fragments.ShowProfileImageFragment
import com.graduation.mawruth.utils.SessionProvider

class ProfileActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityProfileBinding
    private lateinit var uri: Uri
    var dialog = ShowProfileImageFragment()
    private var launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data?.data != null) {
            uri = it.data?.data!!
            val bundle = Bundle()
            bundle.putString("uri", uri.toString())
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.editImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            launcher.launch(intent)
        }
        initViews()

    }

    private fun initViews() {
        viewBinding.profPicCard.setOnClickListener {
            dialog.show(supportFragmentManager, "")
        }
        viewBinding.name.text = SessionProvider.user?.userName
        viewBinding.email.text = SessionProvider.user?.email
    }


}