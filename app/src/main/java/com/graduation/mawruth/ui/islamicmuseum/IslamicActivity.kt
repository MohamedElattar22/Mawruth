package com.graduation.mawruth.ui.islamicmuseum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.graduation.mawruth.databinding.ActivityIslamicBinding
import com.graduation.mawruth.ui.home.HomeViewPager
import com.graduation.mawruth.ui.home.TestViewPagerObject

class IslamicActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityIslamicBinding
    lateinit var adapter: HomeViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityIslamicBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        adapter = HomeViewPager(TestViewPagerObject.getList())
        viewBinding.viewPager.adapter = adapter
        TabLayoutMediator(
            viewBinding.tabLayout,
            viewBinding.viewPager,
            true,
            true
        ) { tab, position ->
        }.attach()

    }

}