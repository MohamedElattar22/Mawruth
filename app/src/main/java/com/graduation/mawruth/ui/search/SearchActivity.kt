package com.graduation.mawruth.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.databinding.ActivitySearchBinding
import com.graduation.mawruth.ui.museumDetails.MuseumDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private var adapter = SearchAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivitySearchBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewBinding.vm = viewModel
        setContentView(viewBinding.root)
        initViews()

    }

    private fun initViews() {
        viewBinding.materialButton2.setOnClickListener {
            viewBinding.searchCont.text?.clear()
        }
        viewBinding.resultRV.adapter = adapter
        subToLive()
        viewBinding.materialButton2.setOnClickListener {
            viewModel.searchOnMuseumsByName()
        }
        viewBinding.closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun subToLive() {
        viewModel.museumList.observe(this) {
            it?.data?.let { data ->
                adapter.bindMuseumList(data)
                adapter.onMuseumClickListener =
                    SearchAdapter.OnMuseumClickListener { museumData, pos ->
                        val intent = Intent(this, MuseumDetailsActivity::class.java)
                        intent.putExtra("museumName", museumData.name.toString())
                        intent.putExtra("museumId", museumData.id.toString())
                        intent.putExtra("museumDesc", museumData.description.toString())
                        intent.putExtra(
                            "museumImage",
                            museumData.images?.get(0)?.imagePath.toString()
                        )
                        intent.putExtra("museumStreet", museumData.street.toString())
                        intent.putExtra("museumLoc", museumData.city.toString())
                        startActivity(intent)

                    }
            }
            viewBinding.notFoundContent.isVisible = it?.data?.size == 0
            viewBinding.searchResultCntTV.text = "${it?.data?.size.toString()} نتيجة"
            Log.d("searhLog", it?.data.toString())
        }
    }
}