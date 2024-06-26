package com.graduation.mawruth.ui.favourities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityFavouriteBinding
import com.graduation.mawruth.ui.home.MuseumRecyclerAdapter
import com.graduation.mawruth.ui.museumDetails.MuseumDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity() {
    private val museumRecyclerAdapter = MuseumRecyclerAdapter(listOf())
    lateinit var viewBinding: ActivityFavouriteBinding
    lateinit var viewModel: FavouriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initviews()
    viewBinding.museumBtn.setOnClickListener {
        val colorselected = ContextCompat.getColor(this, R.color.mainBtn)
        val colordefault = ContextCompat.getColor(this, R.color.whiteSand)
        viewBinding.museumBtn.setBackgroundColor(colorselected)
        viewBinding.piecesBtn.setBackgroundColor(colordefault)
        getallmuseums()

    }
        viewBinding.piecesBtn.setOnClickListener {
            val colorselected = ContextCompat.getColor(this, R.color.mainBtn)
            val colordefault = ContextCompat.getColor(this, R.color.whiteSand)
            viewBinding.piecesBtn.setBackgroundColor(colorselected)
            viewBinding.museumBtn.setBackgroundColor(colordefault)
//viewBinding.favouriteRv.visibility= View.GONE

        }
    }
    override fun onStart() {
        super.onStart()
        observeLiveData()
        getallmuseums()

    }
    private fun observeLiveData() {
        viewModel.museumData.observe(this) {
            museumRecyclerAdapter.bindMuseumsList(it?.data)
        }
    }
fun initviews() {

        viewBinding = ActivityFavouriteBinding.inflate(layoutInflater)
    viewBinding.favouriteRv.adapter=museumRecyclerAdapter
        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        setContentView(viewBinding.root)
        val colordefault = ContextCompat.getColor(this, R.color.mainBtn)
        viewBinding.museumBtn.setBackgroundColor(colordefault)
    museumRecyclerAdapter.onMuseumClickListener = MuseumRecyclerAdapter
        .OnMuseumClickListener { museumDto, position ->
            val intent = Intent(this, MuseumDetailsActivity::class.java)
            intent.putExtra("museumName", museumDto.name)
            intent.putExtra("museumLoc", museumDto.city)
            intent.putExtra("museumStreet", museumDto.street)
            intent.putExtra("museumId", museumDto.id)
            Log.d("museumIdMain", museumDto.id.toString())
            intent.putExtra("museumCountry", museumDto.city)
            intent.putExtra("museumDesc", museumDto.description)
            intent.putExtra("museumWork", "")
            intent.putExtra("museumImage", museumDto.images?.get(0)?.imagePath)
            intent.putExtra("museumType1", museumDto.categories?.get(0)?.museumCategory?.name)
            intent.putExtra("museumType2", museumDto.categories?.get(1)?.museumCategory?.name)
            startActivity(intent)
        }

    }

    fun getallmuseums() {
        viewModel.museumData
     //   viewModel.getMuseumData()
        viewModel.getMuseumData()

    }
}