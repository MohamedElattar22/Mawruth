package com.graduation.mawruth.ui.home.musumsbytype

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.graduation.mawruth.databinding.ActivityCategoryMuseumBinding
import com.graduation.mawruth.ui.museumDetails.MuseumDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryMuseumActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCategoryMuseumBinding

    private lateinit var viewModel: CategoryMuseumsViewModel
    val adapter = MuseumByTypeAdapter(listOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCategoryMuseumBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CategoryMuseumsViewModel::class.java]
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        val typeId = intent.getStringExtra("typeId")
        viewModel.getMuseumByType(typeId!!)
        viewBinding.content.MusRV.adapter = adapter
        observeLiveData()
        adapter.onMuseumClickListener =
            MuseumByTypeAdapter.OnMuseumClickListener { museumDataDto, _ ->
                val intent = Intent(this@CategoryMuseumActivity, MuseumDetailsActivity::class.java)
                intent.putExtra("museumName", museumDataDto.name)
                intent.putExtra("museumLoc", museumDataDto.city)
                intent.putExtra("museumStreet", museumDataDto.street)
                intent.putExtra("museumId", museumDataDto.id)
                intent.putExtra("museumCountry", museumDataDto.city)
                intent.putExtra("museumDesc", museumDataDto.description)
                intent.putExtra("museumWork", "")
                intent.putExtra("museumImage", museumDataDto.images?.get(0)?.imagePath)
                intent.putExtra(
                    "museumType1",
                    museumDataDto.categories?.get(0)?.museumCategory?.name
                )
                intent.putExtra(
                    "museumType2",
                    museumDataDto.categories?.get(1)?.museumCategory?.name
                )
                startActivity(intent)
            }
    }

    private fun observeLiveData() {
        viewModel.museumData.observe(this) {
            adapter.bindMuseumsList(it?.data)
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