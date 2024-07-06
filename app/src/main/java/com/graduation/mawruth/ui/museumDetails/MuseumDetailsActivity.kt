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
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityMuseumDetailsBinding
import com.graduation.mawruth.ui.islamicmuseum.IslamicActivity
import com.graduation.mawruth.ui.notifications.NotificationActivity
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
        viewBinding.piecesRV.adapter = adapter
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val museumID = intent.getStringExtra("museumId")
        viewModel.getMuseumPieces(museumID!!.toInt())
        viewModel.getReviews(museumID.toInt())

        Log.d("museumId", museumID.toString())
        adapter.itemClick = PiecesAdapter.OnPieceClickListener { data, _ ->
            val intent = Intent(this@MuseumDetailsActivity, PieceDetailsActivity::class.java)
            intent.putExtra("title", data.name.toString())
            val museumName = viewBinding.museumName.text.toString()
            intent.putExtra("pieceAR", data.arPath.toString())
            intent.putExtra("idPiece", data.id.toString())
            intent.putExtra("musName", museumName)
            intent.putExtra("description", data.description.toString())
            intent.putExtra("image", data.image.toString())
            Log.d("LogPieces", data.name.toString())
            intent.putExtra("isMaster", data.isMasterpiece)
            startActivity(intent)
        }


        viewBinding.museumName.text = intent.getStringExtra("museumName")
        viewBinding.street.text = intent.getStringExtra("museumStreet")
        viewBinding.reviewRec.adapter = reviewsRecyclerAdapter
        viewBinding.reviewContainer.movementMethod = ScrollingMovementMethod()
        museumId = intent.getIntExtra("museumId", 0).toInt()
        Log.d("ass", museumID.toString())
        if (museumID.toString() == "49") {
            viewBinding.bottomAppBar.isVisible = true
            viewBinding.navigateMuseum.isVisible = true
            viewBinding.navigateMuseum.setOnClickListener {
                navigateToIslamicMuseum()
            }
        }


        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        if (!sharedPreferences.contains("userInfo")) {
            viewBinding.review.isVisible = false
            viewBinding.sendReviewBtn.isVisible = false
        }
        viewModel.getMuseumById(museumId)
        viewBinding
            .musLoc
            .text =
            "${intent.getStringExtra("museumStreet")}-${intent.getStringExtra("museumLoc")}"

        viewBinding.chip1.text = "مصري"
        viewBinding.descr.text = intent.getStringExtra("museumDesc")
        viewBinding.workTimeTV.text = intent.getStringExtra("museumWork")
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

        viewBinding.sendReview.setOnClickListener {
            handleReviews()
        }


        viewBinding.chip1.typeface = Typeface.create(
            ResourcesCompat.getFont(this, R.font.cairo_medium), Typeface.NORMAL
        )


        viewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        viewBinding.toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.notification) {
                navigateToNotification()
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun navigateToNotification() {
        val intent = Intent(this, NotificationActivity::class.java)
        startActivity(intent)

    }

    private fun navigateToIslamicMuseum() {
        val navigate = Intent(this, IslamicActivity::class.java)
        startActivity(navigate)
        finish()
    }


    private fun handleReviews() {
        if (viewBinding.reviewContainer.text.isNullOrBlank()) return
        viewModel.sendReview(viewBinding.reviewContainer.text.toString().trim(), museumId)

    }


    private fun observeToLiveData() {
        viewModel.piecesList.observe(this) {
            adapter.bindPiecesList(it?.data!!)
        }
//        viewModel.error.observe(this) {
//            if (it) {
//                Log.e("el3ttarError", it.toString())
//                Snackbar.make(
//                    this,
//                    viewBinding.root,
//                    "حدث خطأ ما",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            }
//
//        }
        viewModel.reviewLiveData.observe(this) {
//            reviewsRecyclerAdapter.bindSingleReview(it)
//                viewBinding..reviewContainer.text!!.clear()
            Toast.makeText(this, "Review Add Successfully", Toast.LENGTH_SHORT).show()
        }
        viewModel.reviewListLiveData.observe(this) {
//            reviewsRecyclerAdapter.bindReviewsList(it?.data?.toMutableList())
            Log.e("review", it.toString())
        }

    }
}