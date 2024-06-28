package com.graduation.mawruth.ui.islamicmuseum.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.graduation.mawruth.databinding.FragmentIslamicMuseumHomeBinding
import com.graduation.mawruth.ui.home.viewpager.HomeViewPager
import com.graduation.mawruth.ui.home.viewpager.TestViewPagerObject
import com.graduation.mawruth.ui.islamicmuseum.fragments.home.halls.HallsAdapter
import com.graduation.mawruth.ui.islamicmuseum.stories.StoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IslamicMuseumHomeFragment : Fragment() {
    private lateinit var viewBinding: FragmentIslamicMuseumHomeBinding
    private lateinit var viewModel: IslamicMuseumHomeFragmentViewModel
    private lateinit var hallsAdapter: HallsAdapter
    lateinit var adapter: HomeViewPager
    private lateinit var storiesAdapter: StoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentIslamicMuseumHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[IslamicMuseumHomeFragmentViewModel::class.java]
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        storiesAdapter = StoriesAdapter(listOf())
        hallsAdapter = HallsAdapter(listOf())
        viewBinding.islamicContent.stories.adapter = storiesAdapter
        viewBinding.islamicContent.hallsRecycler.adapter = hallsAdapter
        adapter = HomeViewPager(TestViewPagerObject.list)
        viewBinding.viewPager.adapter = adapter
        TabLayoutMediator(
            viewBinding.tabLayout,
            viewBinding.viewPager,
            true,
            true
        ) { tab, position ->
        }.attach()

        viewModel.getStories(1)
        viewModel.getHalls(6)
        subscribeToLive()
    }

    private fun subscribeToLive() {
        viewModel.storiesList.observe(viewLifecycleOwner) { storyData ->
            storiesAdapter.bindStoriesList(storyData.data!!)
        }
        viewModel.hallsList.observe(viewLifecycleOwner) { hallsData ->
            hallsAdapter.bindHallsList(hallsData.data!!)

        }
    }

}