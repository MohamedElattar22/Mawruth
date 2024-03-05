package com.graduation.mawruth.ui.museumDetails

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
import com.graduation.mawruth.ui.arActivity.AgumentedRealityActivity
import com.graduation.mawruth.ui.pieceDetails.PieceDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MuseumDetailsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMuseumDetailsBinding
    private lateinit var viewModel: MuseumDetailsViewModel
    private var adapter = PiecesAdapter(listOf())
    val animator: ValueAnimator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMuseumDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[MuseumDetailsViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        viewBinding.lay.piecesRV.adapter = adapter
        WindowCompat.setDecorFitsSystemWindows(window, false)
        viewBinding.museumName.text = intent.getStringExtra("museumName")
        viewBinding.lay.street.text = intent.getStringExtra("museumStreet")
        val museumId = intent.getIntExtra("museumId", 0)
        Log.d("musId", museumId.toString())
        viewModel.getMuseumById(museumId)
        observeToLiveData()
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



        viewBinding.lay.chip1.typeface = Typeface.create(
            ResourcesCompat.getFont(this, R.font.cairo_medium), Typeface.NORMAL
        )
        animator()
        viewBinding.fab.setOnClickListener {
            navigateToAR()
        }
        viewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }





    }

    private fun animator() {

        viewBinding.scroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                //scrolling down
                viewBinding.fab.shrink()
            } else if (scrollY < oldScrollY) {
                // scrolling up
                viewBinding.fab.extend()
            }


        }

    }

    private fun navigateToAR() {
        val start = Intent(this, AgumentedRealityActivity::class.java)
        startActivity(start)
    }

    private fun observeToLiveData() {
        viewModel.infoLiveData.observe(this) {
            adapter.bindPiecesList(it?.pieces)
            Log.d("pieces", it?.pieces?.get(0).toString())
            Log.d("piecesCount", adapter.itemCount.toString())

            adapter.itemClick = PiecesAdapter.OnPieceClickListener { data, position ->
                val intent = Intent(this@MuseumDetailsActivity, PieceDetailsActivity::class.java)
                intent.putExtra("title", data.name)
                intent.putExtra("age", data.masterPiece)
                intent.putExtra("description", data.description)

                intent.putExtra("image", data.images.toString())
                startActivity(intent)
            }
        }
        viewModel.error.observe(this) {

            Snackbar.make(
                this,
                viewBinding.root,
                it,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }


}