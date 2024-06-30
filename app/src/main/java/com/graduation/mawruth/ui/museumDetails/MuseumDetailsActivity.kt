package com.graduation.mawruth.ui.museumDetails

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityMuseumDetailsBinding
import com.graduation.mawruth.ui.pieceDetails.PieceDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MuseumDetailsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMuseumDetailsBinding
    private lateinit var viewModel: MuseumDetailsViewModel
    private val reviewsRecyclerAdapter = ReviewsRecyclerAdapter(mutableListOf())
    private var adapter = PiecesAdapter(listOf())
    private var museumId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMuseumDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[MuseumDetailsViewModel::class.java]
        initViews()
        observeToLiveData()
    }

    private fun initViews() {
        viewBinding.lay.piecesRV.adapter = adapter
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val museumID = intent.getStringExtra("museumId")
//
        Log.d("museumId", museumID.toString())
        viewBinding.museumName.text = intent.getStringExtra("museumName")
        viewBinding.lay.street.text = intent.getStringExtra("museumStreet")
        viewBinding.lay.reviewRec.adapter = reviewsRecyclerAdapter
        viewBinding.lay.reviewContainer.movementMethod = ScrollingMovementMethod()
        museumId = intent.getIntExtra("museumId", 0)
        viewModel.getMuseumPieces(museumId)
        viewModel.getReviews(museumId)

        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        if (!sharedPreferences.contains("userInfo")) {
            viewBinding.lay.review.isVisible = false
            viewBinding.lay.sendReviewBtn.isVisible = false
        }
        viewModel.getMuseumById(museumId)
        viewBinding
            .musLoc
            .text =
            "${intent.getStringExtra("museumStreet")}-${intent.getStringExtra("museumLoc")}"
        viewBinding.lay.chip1.text = "مصري"
        viewBinding.lay.descr.text = intent.getStringExtra("museumDesc")
        viewBinding.lay.workTimeTV.text = intent.getStringExtra("museumWork")
        Glide.with(this)
            .load(intent.getStringExtra("museumImage").toString())
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    viewBinding.musImage.background = resource
                }
            })

        viewBinding.lay.sendReview.setOnClickListener {
            handleReviews()
        }


        viewBinding.lay.chip1.typeface = Typeface.create(
            ResourcesCompat.getFont(this, R.font.cairo_medium), Typeface.NORMAL
        )


        viewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }


    private fun handleReviews() {
        if (viewBinding.lay.reviewContainer.text.isNullOrBlank()) return
        viewModel.sendReview(viewBinding.lay.reviewContainer.text.toString().trim(), museumId)

    }


    private fun observeToLiveData() {
        viewModel.piecesList.observe(this) {
            adapter.bindPiecesList(listOf(it))
            adapter.itemClick = PiecesAdapter.OnPieceClickListener { data, _ ->
                val intent = Intent(this@MuseumDetailsActivity, PieceDetailsActivity::class.java)
                intent.putExtra("title", data.data?.get(0)?.name)
                Log.d("livea", data.data?.get(0)?.name.toString())
                val museumName = viewBinding.museumName.text.toString()
                intent.putExtra("pieceAR", data.data?.get(0)?.arPath)

                intent.putExtra("idPiece", data.data?.get(0)?.id)
                intent.putExtra("musName", museumName)
                intent.putExtra("description", data.data?.get(0)?.description)
                intent.putExtra("image", data.data?.get(0)?.image)
                intent.putExtra("isMaster", data.data?.get(0)?.isMasterpiece)
                startActivity(intent)
            }
            viewModel.infoLiveData.observe(this) {

//            adapter.bindPiecesList(it?.pieces)
//            Log.d("pieces", it?.pieces?.get(0).toString())
//            Log.d("piecesCount", adapter.itemCount.toString())


//            }
            }
            viewModel.error.observe(this) {

                Log.e("el3ttarError", it.toString())
                Snackbar.make(
                    this,
                    viewBinding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            viewModel.reviewLiveData.observe(this) {
//            reviewsRecyclerAdapter.bindSingleReview(it)
                viewBinding.lay.reviewContainer.text!!.clear()
                Toast.makeText(this, "Review Add Successfully", Toast.LENGTH_SHORT).show()
            }
            viewModel.reviewListLiveData.observe(this) {
//            reviewsRecyclerAdapter.bindReviewsList(it?.data?.toMutableList())
                Log.e("review", it.toString())
            }
        }


    }
}