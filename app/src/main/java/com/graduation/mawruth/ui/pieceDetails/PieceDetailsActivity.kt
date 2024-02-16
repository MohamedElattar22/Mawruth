package com.graduation.mawruth.ui.pieceDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.graduation.mawruth.databinding.ActivityPieceDetailsBinding

class PieceDetailsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPieceDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPieceDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        getData()
    }

    private fun getData() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val title = intent.getStringExtra("title")
        viewBinding.textView.text = title
        val image = intent.getIntExtra("image", 0)
        viewBinding.image2.setImageResource(image)
        val age = intent.getStringExtra("age")
        viewBinding.pieceAgeTV.text = age
        val des = intent.getStringExtra("description")
        viewBinding.details.descritptionTV.text = des


    }
}