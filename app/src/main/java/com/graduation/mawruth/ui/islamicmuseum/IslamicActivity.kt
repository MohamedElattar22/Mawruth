package com.graduation.mawruth.ui.islamicmuseum

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityIslamicBinding
import com.graduation.mawruth.ui.islamicmuseum.fragments.discover.IslamicMuseumDiscoverFragment
import com.graduation.mawruth.ui.islamicmuseum.fragments.favourite.IslamicMuseumFavouriteFragment
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.IslamicMuseumHomeFragment
import com.graduation.mawruth.ui.islamicmuseum.fragments.more.MoreFeaturesFragment
import com.graduation.mawruth.ui.virtualtours.VirtualToursActivity
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
        WindowCompat.setDecorFitsSystemWindows(window, false)


    }

    private fun navigateFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragment, fragment)
            .commit()
    }

    private fun setupBottomNavigation() {
        viewBinding.add.setOnClickListener {
            val intent = Intent(this, VirtualToursActivity::class.java)
            startActivity(intent)
        }
        viewBinding.bottomNavBar.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.homeIconBottom) {

                navigateFragment(IslamicMuseumHomeFragment())

            } else if (item.itemId == R.id.discoverIconBottom) {
                navigateFragment(IslamicMuseumDiscoverFragment())
            } else if (item.itemId == R.id.fav_nav_barIconBottom) {
                navigateFragment(IslamicMuseumFavouriteFragment())
            } else if (item.itemId == R.id.moreIconBottom) {
                showDataSheet()
            }

            return@setOnItemSelectedListener true
        }

    }

    private fun showDataSheet() {
        val dataBottomSheet = MoreFeaturesFragment()
        dataBottomSheet.show(supportFragmentManager, "")
    }

}