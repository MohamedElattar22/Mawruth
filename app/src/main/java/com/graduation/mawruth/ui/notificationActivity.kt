package com.graduation.mawruth.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityFavouriteBinding
import com.graduation.mawruth.databinding.ActivityNotificationBinding

class notificationActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityNotificationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinding.root)

        }
    }
