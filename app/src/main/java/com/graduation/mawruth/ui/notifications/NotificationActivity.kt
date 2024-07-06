package com.graduation.mawruth.ui.notifications

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.graduation.mawruth.databinding.ActivityNotification2Binding

class NotificationActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNotification2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNotification2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(viewBinding.root)
        initViews()

    }

    private fun initViews() {
        viewBinding.closeBtn.setOnClickListener {
            finish()
        }
    }
}