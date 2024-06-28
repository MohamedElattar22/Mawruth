package com.graduation.mawruth.ui.story

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.graduation.mawruth.databinding.ActivityStoryBinding

class storyActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

    }
}