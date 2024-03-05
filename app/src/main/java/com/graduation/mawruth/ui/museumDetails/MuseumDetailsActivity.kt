package com.graduation.mawruth.ui.museumDetails

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityMuseumDetailsBinding
import com.graduation.mawruth.ui.arActivity.AgumentedRealityActivity
import com.graduation.mawruth.ui.pieceDetails.PieceDetailsActivity


class MuseumDetailsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMuseumDetailsBinding
    private lateinit var adapter: PiecesAdapter
    val animator: ValueAnimator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMuseumDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        viewBinding.museumName.text = intent.getStringExtra("museumName")
        viewBinding.lay.street.text = intent.getStringExtra("museumStreet")
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
            navigatetoAR()
        }
        viewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        adapter = PiecesAdapter(testData.list)
        viewBinding.lay.piecesRV.adapter = adapter
        adapter.itemClick = PiecesAdapter.OnPieceClickListener { data, position ->
            val intent = Intent(this@MuseumDetailsActivity, PieceDetailsActivity::class.java)
            intent.putExtra("title", data.pieceTitle)
            intent.putExtra("age", data.pieceAge)
            intent.putExtra("description", data.description)

            intent.putExtra("image", data.image)
            startActivity(intent)
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

    fun navigatetoAR() {
        val start = Intent(this, AgumentedRealityActivity::class.java)
        startActivity(start)
    }

}