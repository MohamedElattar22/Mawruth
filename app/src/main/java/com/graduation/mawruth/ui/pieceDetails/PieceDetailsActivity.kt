package com.graduation.mawruth.ui.pieceDetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.graduation.domain.model.pieces.PiecesItem
import com.graduation.mawruth.databinding.ActivityPieceDetailsBinding
import com.graduation.mawruth.ui.arActivity.AgumentedRealityActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PieceDetailsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPieceDetailsBinding
    private lateinit var viewModel: PieceDataViewModel
    private var pieceName: String? = null
    private var pieceData = PiecesItem()
    private var pieceDes: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPieceDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[PieceDataViewModel::class.java]
        getData()
        observeLiveData()
    }

    override fun onStart() {
        super.onStart()
        val id = intent.getStringExtra("idPiece").toString()
        Log.d("id", id)
    }

    private fun getData() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val title = intent.getStringExtra("title").toString()
        pieceName = title.toString()
        viewBinding.pieceNameTV.text = title
        Log.d("pieceName", title)
        val image = intent.getStringExtra("image").toString()
        Glide.with(this)
            .load(image)
            .into(viewBinding.image2)
        Log.d("pieceName", image)
        val des = intent.getStringExtra("description")
        pieceDes = des.toString()
        viewBinding.details.descritptionTV.text = des

        val museumName = intent.getStringExtra("musName")
        viewBinding.details.museumNameTV.text = museumName.toString()
        val pieceAr = intent.getStringExtra("pieceAR").toString()
        Log.d("moha", intent.getBooleanExtra("isMaster", false).toString())
        val isMasterPiece = intent.getBooleanExtra("isMaster", false)
        viewBinding.navigateToAR.isVisible = isMasterPiece
        viewBinding.navigateToAR.setOnClickListener {
            val start = Intent(this, AgumentedRealityActivity::class.java)
            start.putExtra("agmunted", pieceAr)
            start.putExtra("pieceName", title)
            start.putExtra("pieceDes", pieceDes.toString())
            startActivity(start)
        }

    }

//    private fun animator() {
//
//        viewBinding.scroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            if (scrollY > oldScrollY) {
//                //scrolling down
//                viewBinding.fab.shrink()
//            } else if (scrollY < oldScrollY) {
//                // scrolling up
//                viewBinding.fab.extend()
//            }
//
//
//        }
//
//    }

    private fun observeLiveData() {
        viewModel.pieceData.observe(this) {
            pieceData = it!!
            Log.d("wa7ed", pieceData.toString())
        }
    }

}