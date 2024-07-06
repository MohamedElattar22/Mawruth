package com.graduation.mawruth.ui.islamicmuseum.fragments.home.collections

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.graduation.mawruth.databinding.ActivityCollectionPiecesBinding
import com.graduation.mawruth.ui.museumDetails.PiecesAdapter
import com.graduation.mawruth.ui.pieceDetails.PieceDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionPiecesActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCollectionPiecesBinding
    private lateinit var viewModel: CollectionPiecesViewModel
    private lateinit var piecesAdapter: PiecesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityCollectionPiecesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CollectionPiecesViewModel::class.java]
        setContentView(viewBinding.root)
        initViews()

    }

    private fun initViews() {
        settingPiecesAdapter()
        setDataRetrieved()
        subscribeToLiveData()
    }

    private fun setDataRetrieved() {
        val museumID = intent.getStringExtra("museumID")?.toInt()
        val collectionID = intent.getStringExtra("collectionID")?.toInt()
        viewModel.getPiecesOfCollection(museumID!!, collectionID!!)

    }

    private fun subscribeToLiveData() {
        viewModel.piecesList.observe(this) {
            Log.d("ones", it.toString())
            if (it?.data!!.isNullOrEmpty()) {
                piecesAdapter.bindPiecesList(listOf())
                viewBinding.piecesOfCollectionRV.isVisible = false
                viewBinding.failContent.isVisible = true
            }
            piecesAdapter.bindPiecesList(it.data!!)
            viewBinding.failContent.isVisible = false
        }
        viewModel.error.observe(this) {
            if (it) {
                Snackbar.make(
                    this,
                    viewBinding.root,
                    "حدث خطأ ما",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun settingPiecesAdapter() {
        piecesAdapter = PiecesAdapter(listOf())
        viewBinding.piecesOfCollectionRV.adapter = piecesAdapter
        piecesAdapter.itemClick = PiecesAdapter.OnPieceClickListener { data, position ->
            val intent = Intent(this, PieceDetailsActivity::class.java)
            intent.putExtra("title", data.name.toString())
            intent.putExtra("image", data.image.toString())
            intent.putExtra("idPiece", data.id.toString())
            intent.putExtra("description", data.description.toString())
            intent.putExtra("pieceAR", data.arPath.toString())
            intent.putExtra("isMaster", data.isMasterpiece)
            startActivity(intent)
        }
    }

}