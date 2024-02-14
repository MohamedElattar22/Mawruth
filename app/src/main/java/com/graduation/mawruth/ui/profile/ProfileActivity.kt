package com.graduation.mawruth.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.graduation.domain.model.userlogin.UserLoginDto
import com.graduation.mawruth.databinding.ActivityProfileBinding
import com.graduation.mawruth.ui.profile.fragments.ShowProfileImageFragment


class ProfileActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityProfileBinding
    private lateinit var uri: Uri
    var user: UserLoginDto? = null
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
        viewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }


    private fun initViews() {
        viewBinding.profPicCard.setOnClickListener {
            dialog.show(supportFragmentManager, "")
        }
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        sharedPreferences.getString("userData", null)?.let {
            user = Gson().fromJson(it, UserLoginDto::class.java)
            viewBinding.name.text = user?.userName
            viewBinding.email.text = user?.email
        }

    }


}