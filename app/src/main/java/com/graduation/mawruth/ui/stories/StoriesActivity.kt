package com.graduation.mawruth.ui.stories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
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
    }
}