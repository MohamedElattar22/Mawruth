package com.graduation.mawruth.ui.halls

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.graduation.mawruth.databinding.ActivityIslamicMuseumHallsBinding
import com.graduation.mawruth.ui.halls.audio.AudioActivity
import com.graduation.mawruth.ui.museumDetails.PiecesAdapter
import com.graduation.mawruth.ui.pieceDetails.PieceDetailsActivity
import com.graduation.mawruth.ui.virtualtours.VirtualToursActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicMuseumHallsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityIslamicMuseumHallsBinding
    private lateinit var viewModel: hallsViewModel
    private lateinit var piecesAdapter: PiecesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityIslamicMuseumHallsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[hallsViewModel::class.java]
        setContentView(viewBinding.root)
        initViews()

    }

    private fun initViews() {
        getDataFromHome()
        subscribeToLiveData()
        piecesAdapter = PiecesAdapter(listOf())
        viewBinding.recyclerView.adapter = piecesAdapter


    }

    private fun subscribeToLiveData() {
        viewModel.piecesList.observe(this) {
            piecesAdapter.bindPiecesList(it?.data!!)
            piecesAdapter.itemClick = PiecesAdapter.OnPieceClickListener { data, position ->
                val intent = Intent(this, PieceDetailsActivity::class.java)
                intent.putExtra("title", data.name)
                intent.putExtra("image", data.image)
                intent.putExtra("description", data.description)
                intent.putExtra("isMaster", data.isMasterpiece)
                intent.putExtra("pieceAR", data.arPath)
                startActivity(intent)

            }
        }

    }

    private fun getDataFromHome() {
        val hallName = intent.getStringExtra("hallName").toString()
        viewBinding.soundText.text = hallName
        val hallDescription = intent.getStringExtra("hallDescription").toString()
        val hallImage = intent.getStringExtra("hallImage").toString()
        val imagePath = intent.getStringExtra("soundImage").toString()
        val soundPath = intent.getStringExtra("soundPath")
        viewBinding.soundd.setOnClickListener {
            val intent = Intent(this, AudioActivity::class.java)
            intent.putExtra("sound", soundPath)
            intent.putExtra("image", imagePath)
            intent.putExtra("name", hallName)
            startActivity(intent)
        }

        val hallID = intent.getStringExtra("hallID")
        viewModel.getHallByID(hallID!!.toInt())
        viewModel.getPiecesOFHall(49, hallID.toInt())
        viewBinding.hallName.text = hallName
        viewBinding.hallDescription.text = hallDescription
        Glide.with(this)
            .load(hallImage)
            .into(viewBinding.halliImage)

        viewBinding.toVirtualTours.setOnClickListener {
            val intent = Intent(this, VirtualToursActivity::class.java)
            startActivity(intent)
        }

    }

}