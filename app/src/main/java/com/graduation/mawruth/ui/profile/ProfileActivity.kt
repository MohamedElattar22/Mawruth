package com.graduation.mawruth.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.graduation.domain.model.authenticationuser.Data
import com.graduation.mawruth.databinding.ActivityProfileBinding
import com.graduation.mawruth.ui.profile.fragments.showprofile.ShowProfileImageFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityProfileBinding
    private lateinit var uri: Uri
    var user: Data? = null
    val bundle = Bundle()
    private var dialog = ShowProfileImageFragment()
    private var launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK && it.data?.data != null) {
            uri = it.data?.data!!
            bundle.putString("uri", uri.toString())
            bundle.putInt("out", 1)
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


    fun initViews() {
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        sharedPreferences.getString("userData", null)?.let {
            user = Gson().fromJson(it, Data::class.java)
            Log.d("user", user.toString())
            viewBinding.name.text = user?.user?.name
            viewBinding.email.text = "@${user?.user?.email}"
            Glide.with(this).load(user?.user?.image).into(viewBinding.profPic)
        }
        viewBinding.profPicCard.setOnClickListener {
            bundle.putString("uri", user?.user?.image.toString())
            bundle.putInt("out", 0)
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, "")

        }
    }


}