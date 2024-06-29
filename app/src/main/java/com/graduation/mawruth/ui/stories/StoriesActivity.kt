package com.graduation.mawruth.ui.stories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.graduation.mawruth.databinding.StoriesLayoutBinding

class StoriesActivity : AppCompatActivity() {
    private lateinit var viewBinding: StoriesLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = StoriesLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        retrievingStoryData()
    }

    private fun retrievingStoryData() {
        val storyTitle = intent.getStringExtra("storyName").toString()
        val storyContent = intent.getStringExtra("storyContent").toString()
        val storyImage = intent.getStringExtra("storyImage").toString()
        viewBinding.hkayaNameTV.text = storyTitle
        viewBinding.storyTV.text = storyContent
        Glide.with(this)
            .load(storyImage)
            .into(viewBinding.imageView7)
    }
}