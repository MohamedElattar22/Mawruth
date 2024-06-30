package com.graduation.mawruth.ui.halls

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.graduation.mawruth.databinding.ActivityIslamicMuseumHallsBinding
import com.graduation.mawruth.ui.halls.audio.AudioActivity
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
//            val data = hallData.data?.get(0)
//            intent.putExtra("soundImage" , data?.soundImage)
//            intent.putExtra("soundPath" , data?.soundPath)
//            intent.putExtra("hallName" , data?.name)
//
//        }
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
        viewBinding.hallName.text = hallName
        viewBinding.hallDescription.text = hallDescription
        Glide.with(this)
            .load(hallImage)
            .into(viewBinding.halliImage)

    }

    private fun navigateToSound() {

    }

}