package com.graduation.mawruth.ui.favourites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.graduation.mawruth.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFavouriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}