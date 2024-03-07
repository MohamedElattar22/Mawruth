package com.graduation.mawruth.ui.museumDetails

import android.animation.ValueAnimator
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
    var museumId: Int = 0
    val animator: ValueAnimator? = null
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
        viewBinding.museumName.text = intent.getStringExtra("museumName")
        viewBinding.lay.street.text = intent.getStringExtra("museumStreet")
        viewBinding.lay.reviewRec.adapter = reviewsRecyclerAdapter
        viewBinding.lay.reviewContainer.movementMethod = ScrollingMovementMethod()
        museumId = intent.getIntExtra("museumId", 0)
        viewModel.getReviews(museumId)

        viewModel.getMuseumById(museumId)
        viewBinding.musLoc.text =
            "${intent.getStringExtra("museumCountry")}-${intent.getStringExtra("museumLoc")}"
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
        viewModel.infoLiveData.observe(this) {
            adapter.bindPiecesList(it?.pieces)
            Log.d("pieces", it?.pieces?.get(0).toString())
            Log.d("piecesCount", adapter.itemCount.toString())

            adapter.itemClick = PiecesAdapter.OnPieceClickListener { data, position ->
                val intent = Intent(this@MuseumDetailsActivity, PieceDetailsActivity::class.java)
                intent.putExtra("title", data.name)
                val museumName = viewBinding.museumName.text.toString()
                intent.putExtra("pieceAR", data.arPath.toString())
                intent.putExtra("idPiece", data.iD.toString())
                intent.putExtra("musName", museumName)
                intent.putExtra("description", data.description)
                intent.putExtra("image", data.images?.get(0)?.imagePath.toString())
                startActivity(intent)
            }
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
            reviewsRecyclerAdapter.bindSingleReview(it!!)
            viewBinding.lay.reviewContainer.text!!.clear()
            Toast.makeText(this, "Review Add Successfully", Toast.LENGTH_SHORT).show()
        }
        viewModel.reviewListLiveData.observe(this) {
            reviewsRecyclerAdapter.bindReviewsList(it?.toMutableList())
            Log.e("review", it.toString())
        }
    }


}