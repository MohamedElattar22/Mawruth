package com.graduation.mawruth.ui.virtualtours

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.graduation.mawruth.databinding.ActivityVirtualToursBinding

class VirtualToursActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityVirtualToursBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityVirtualToursBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initViews() {

        viewBinding.virtualTours.settings.javaScriptEnabled = true
        viewBinding.virtualTours.loadUrl("https://mpembed.com/show/?m=GLcinPBnEet&mpu=497")

    }


}