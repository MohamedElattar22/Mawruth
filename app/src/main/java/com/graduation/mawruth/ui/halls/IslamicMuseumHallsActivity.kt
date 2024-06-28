package com.graduation.mawruth.ui.halls

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.graduation.mawruth.databinding.ActivityIslamicMuseumHallsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicMuseumHallsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityIslamicMuseumHallsBinding
    private lateinit var viewModel: hallsViewModel
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

    }

    private fun subscribeToLiveData() {
//        viewModel.hallList.observe(this){ hallData->
//
//
//        }
    }

    private fun getDataFromHome() {
        val hallName = intent.getStringExtra("hallName").toString()
        val hallDescription = intent.getStringExtra("hallDescription").toString()
        val hallImage = intent.getStringExtra("hallImage").toString()
        val hallID = intent.getStringExtra("hallID")
        viewModel.getHallByID(hallID!!.toInt())
        viewBinding.hallName.text = hallName
        viewBinding.hallDescription.text = hallDescription
        Glide.with(this)
            .load(hallImage)
            .into(viewBinding.halliImage)

    }

}