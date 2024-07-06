package com.graduation.mawruth.ui.arActivity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.ar.sceneform.ux.ArFragment
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityAgumentedRealityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgumentedRealityActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAgumentedRealityBinding
    private lateinit var arFragment: ArFragment
    private lateinit var viewModel: ARViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAgumentedRealityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[ARViewModel::class.java]
        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        val arData = intent.getStringExtra("agmunted").toString()
        val pieceName = intent.getStringExtra("pieceName")
        Log.i("pieceAr", arData)
        Log.i("pieceData", pieceName.toString())
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            lifecycleScope.launch(Dispatchers.Unconfined) {
                viewModel.spawnObject(
                    hitResult.createAnchor(),
                    Uri.parse(arData), this@AgumentedRealityActivity, arFragment
                )
            }


        }

        viewBinding.fab.setOnClickListener {
            showDataSheet()
        }

    }


    private fun showDataSheet() {
        val dataBottomSheet = ObjectDataFragment()
        dataBottomSheet.show(supportFragmentManager, "")
    }


}