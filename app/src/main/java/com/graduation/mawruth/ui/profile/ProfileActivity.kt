package com.graduation.mawruth.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.graduation.mawruth.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}