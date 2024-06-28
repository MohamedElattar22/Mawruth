package com.graduation.mawruth.ui.islamicmuseum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityIslamicBinding
import com.graduation.mawruth.ui.islamicmuseum.fragments.discover.IslamicMuseumDiscoverFragment
import com.graduation.mawruth.ui.islamicmuseum.fragments.favourite.IslamicMuseumFavouriteFragment
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.IslamicMuseumHomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityIslamicBinding
    lateinit var viewModel: IslamicMuseumHomeViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityIslamicBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[IslamicMuseumHomeViewModel::class.java]
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        navigateFragment(IslamicMuseumHomeFragment())
        setupBottomNavigation()


    }

    private fun navigateFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragment, fragment)
            .commit()
    }

    private fun setupBottomNavigation() {
        viewBinding.bottomNavBar.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.homeIconBottom) {

                navigateFragment(IslamicMuseumHomeFragment())

            } else if (item.itemId == R.id.discoverIconBottom) {
                navigateFragment(IslamicMuseumDiscoverFragment())
            } else if (item.itemId == R.id.fav_nav_barIconBottom) {
                navigateFragment(IslamicMuseumFavouriteFragment())
            } else if (item.itemId == R.id.moreIconBottom) {
                navigateFragment(IslamicMuseumFavouriteFragment())
            }

            return@setOnItemSelectedListener true
        }

    }

}