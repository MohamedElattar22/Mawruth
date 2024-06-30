package com.graduation.mawruth.ui.stories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.graduation.mawruth.databinding.StoriesLayoutBinding
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.stories.InnerStoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoriesActivity : AppCompatActivity() {
    private lateinit var viewBinding: StoriesLayoutBinding
    private lateinit var viewModel: StoriesViewModel
    private lateinit var storiesAdapter: InnerStoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = StoriesLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[StoriesViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        retrievingStoryData()
        subscribeToLiveData()
        storiesAdapter = InnerStoriesAdapter(listOf())
        viewBinding.otherStoriesRV.adapter = storiesAdapter
        viewModel.getAllStories(49)
    }

    private fun subscribeToLiveData() {
        viewModel.storiesList.observe(this) {
            storiesAdapter.bindStoriesList(it?.data!!)
        }
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