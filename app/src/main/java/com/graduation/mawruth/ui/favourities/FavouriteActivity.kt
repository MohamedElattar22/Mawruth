package com.graduation.mawruth.ui.favourities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityFavouriteBinding
import com.graduation.mawruth.ui.home.HomeActivity
import com.graduation.mawruth.ui.museumDetails.MuseumDetailsActivity
import com.graduation.mawruth.ui.museumDetails.PiecesAdapter
import com.graduation.mawruth.ui.pieceDetails.PieceDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity() {
    private val favouriteMuseumRecyclerAdapter = FavouriteMuseumRecyclerAdapter(listOf())
    private val favouritePiecesRecyclerAdapter = FavouritePiecesRecyclerAdapter(listOf())
    lateinit var viewBinding: ActivityFavouriteBinding
    lateinit var viewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()

        viewBinding.museumBtn.setOnClickListener {
            switchToMuseums()
        }
        viewBinding.piecesBtn.setOnClickListener {
            switchToPieces()
        }
        viewBinding.backArrow.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        observeLiveData()
        getAllMuseums()
    }

    private fun observeLiveData() {
        viewModel.museumData.observe(this) {
            favouriteMuseumRecyclerAdapter.bindMuseumsList(it?.data)
        }
        viewModel.piecedata.observe(this) {
            favouritePiecesRecyclerAdapter.bindPiecesList(it?.data)
        }
    }

    private fun switchToMuseums() {
        setButtonStyles(viewBinding.museumBtn, viewBinding.piecesBtn)
        viewBinding.favouriteRv.adapter = favouriteMuseumRecyclerAdapter
        getAllMuseums()
    }

    private fun switchToPieces() {
        setButtonStyles(viewBinding.piecesBtn, viewBinding.museumBtn)
        viewBinding.favouriteRv.adapter = favouritePiecesRecyclerAdapter
        viewModel.getPiecesData()
    }

    private fun setButtonStyles(selectedButton: Button, defaultButton: Button) {
        val colorSelected = ContextCompat.getColor(this, R.color.mainBtn)
        val colorDefault = ContextCompat.getColor(this, R.color.whiteSand)
        val colorSelectedText = ContextCompat.getColor(this, R.color.white)
        val colorDefaultText = ContextCompat.getColor(this, R.color.black)

        selectedButton.setTextColor(colorSelectedText)
        selectedButton.setBackgroundColor(colorSelected)
        defaultButton.setTextColor(colorDefaultText)
        defaultButton.setBackgroundColor(colorDefault)
    }

    private fun initViews() {
        viewBinding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

        viewBinding.favouriteRv.adapter = favouriteMuseumRecyclerAdapter
        setButtonStyles(viewBinding.museumBtn, viewBinding.piecesBtn)

        favouriteMuseumRecyclerAdapter.onMuseumClickListener = FavouriteMuseumRecyclerAdapter
            .OnMuseumClickListener { museumDto, position ->
                val intent = Intent(this, MuseumDetailsActivity::class.java).apply {
                    putExtra("museumName", museumDto.museum?.name)
                    putExtra("museumLoc", museumDto.museum?.city)
                    putExtra("museumStreet", museumDto.museum?.street)
                    putExtra("museumId", museumDto.id)
                    putExtra("museumCountry", museumDto.museum?.city)
                    putExtra("museumDesc", museumDto.museum?.description)
                    putExtra("museumWork", "")
                    putExtra("museumImage", museumDto.museum?.images?.get(0)?.imagePath)
                    putExtra("museumType1", museumDto.museum?.categories?.get(0)?.museumCategory?.name)
                    putExtra("museumType2", museumDto.museum?.categories?.get(1)?.museumCategory?.name)
                }
                startActivity(intent)
            }

        favouritePiecesRecyclerAdapter.itemClick = FavouritePiecesRecyclerAdapter.OnPieceClickListener { data, _ ->
            val intent = Intent(this, PieceDetailsActivity::class.java).apply {
                putExtra("title", data.piece?.name)
                putExtra("pieceAR", data.piece?.arPath)
                putExtra("idPiece", data.piece?.id)
                putExtra("musName", data.piece?.name)
                putExtra("description", data.piece?.description)
                putExtra("image", data.piece?.image)
                putExtra("isMaster", data.piece?.isMasterpiece)
            }
            startActivity(intent)
        }

        favouriteMuseumRecyclerAdapter.onLoveClickListener = FavouriteMuseumRecyclerAdapter
            .OnMuseumClickListener { museumDto, position ->
                viewModel.deleteMuseumData(museumDto.museumId!!)
                getAllMuseums()
            }
    }

    private fun getAllMuseums() {
        viewModel.getMuseumData()
    }
}
